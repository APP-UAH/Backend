package routes

import com.appuah.API
import com.appuah.DAO.SubjectDAO
import com.appuah.Models.AddSubjectRequest
import com.appuah.Models.ProfessorSubjectsResponse
import com.appuah.Models.SubjectsRequest
import com.appuah.Models.SubjectsResponse
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

const val subjects = "$API/subjects"
const val allSubjects = "$subjects/allsubjects"
const val addSubject = "$subjects/addsubjects"

@KtorExperimentalLocationsAPI
@Location(subjects)
class SubjectsRoute

@KtorExperimentalLocationsAPI
@Location(allSubjects)
class AllSubjectsRoute

@KtorExperimentalLocationsAPI
@Location(addSubject)
class AddSubject
val gson = Gson()

@KtorExperimentalLocationsAPI
fun Route.subjects() {
    get<SubjectsRoute> {
        val user = call.receive<SubjectsRequest>()
        var subjects: List<String> = emptyList()
        when (user.type) {
            1 -> subjects = SubjectDAO().getProfessorSubjects(user.username)
            0 -> subjects = SubjectDAO().getStudentSubjects(user.username)
        }

        val gisi: ArrayList<String> = ArrayList()
        val gic: ArrayList<String> = ArrayList()
        val gii: ArrayList<String> = ArrayList()

        for (i in subjects) {
            when {
                i.contains("GISI") -> {
                    gisi.add(i.split(" ,")[0])
                }
                i.contains("GII") -> {
                    gii.add(i.split(" ,")[0])
                }
                else -> {
                    gic.add(i.split(" ,")[0])
                }
            }
        }
        //println(ProfessorSubjectsResponse(gisi, gii, gic))
        call.respond(HttpStatusCode.Accepted, gson.toJson(ProfessorSubjectsResponse(gisi, gii, gic)))
    }

    get<AllSubjectsRoute> {
        val allSubjects = SubjectDAO().getAllSubjects()
        val gisi: ArrayList<String> = ArrayList()
        val gic: ArrayList<String> = ArrayList()
        val gii: ArrayList<String> = ArrayList()

        for (i in allSubjects) {
            when {
                i.contains("GISI") -> {
                    gisi.add(i.split(" ,").get(0) + i.split(" ,")[2])
                }
                i.contains("GII") -> {
                    gii.add(i.split(" ,").get(0) + i.split(" ,")[2])
                }
                else -> {
                    gic.add(i.split(" ,").get(0) + i.split(" ,")[2])
                }
            }
        }
        val gson = Gson()
        call.respond(HttpStatusCode.Accepted, gson.toJson(SubjectsResponse(gisi, gii, gic)))
    }

    patch<AddSubject> {
        val addSubjectRequest = call.receive<AddSubjectRequest>()
        try {
            when (addSubjectRequest.type) {
                0 -> SubjectDAO().deleteStudentSubject(addSubjectRequest.username)
                1 -> SubjectDAO().deleteProfessorSubject(addSubjectRequest.username)
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e)
        }
        try {
            when (addSubjectRequest.type) {
                0 -> while (addSubjectRequest.subjectCodes.isNotEmpty()) {
                        SubjectDAO().addStudentSubjects(
                            addSubjectRequest.username,
                            addSubjectRequest.plan,
                            addSubjectRequest.subjectCodes[0]
                        )
                        addSubjectRequest.subjectCodes.removeAt(0)
                    }
                1 -> while (addSubjectRequest.subjectCodes.isNotEmpty()) {
                    SubjectDAO().addProfessorSubjects(
                        addSubjectRequest.username,
                        addSubjectRequest.plan,
                        addSubjectRequest.subjectCodes[0]
                    )
                    addSubjectRequest.subjectCodes.removeAt(0)
                }
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e)
        }
    }
}

