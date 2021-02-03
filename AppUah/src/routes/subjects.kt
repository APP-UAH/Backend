package routes

import Models.SubjectsResponse
import com.appuah.API
import com.appuah.DAO.SubjectDAO
import com.appuah.Models.*
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


@KtorExperimentalLocationsAPI
fun Route.subjects() {
    var gson = Gson()
    get<SubjectsRoute> {
        val user = call.receive<SubjectsRequest>()
        // preguntar a dani un subject type o dos diferentes por cada usuario
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
            val allSubjectsPlans= SubjectDAO().getAllPlans()

            val subjects: ArrayList<SubjectsResponse> = ArrayList()
            val plans: ArrayList<String> = ArrayList()
            for (i in allSubjectsPlans){
                if(i !in plans){
                    plans.add(i)
                }
            }
            for(j in allSubjects){
                val nombre = j.split(" ,")[0]
                val plan = j.split(" ,")[1]
                val code = j.split(" ,")[2]
                subjects.add(SubjectsResponse(nombre,plan,code))
            }

            call.respond(gson.toJson(AllSubjectsResponse(plans,subjects)))

    }

    patch<AddSubject> {
        val addSubjectRequest = call.receive<AddSubjectRequest>()
        try {
            when (addSubjectRequest.type) {
                0 -> SubjectDAO().addStudentSubjects(
                    addSubjectRequest.username,
                    addSubjectRequest.plan, addSubjectRequest.subjectCodes
                )
                1 -> SubjectDAO().addProfessorSubjects(
                    addSubjectRequest.username,
                    addSubjectRequest.plan, addSubjectRequest.subjectCodes
                )
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e)
        }
    }
}

