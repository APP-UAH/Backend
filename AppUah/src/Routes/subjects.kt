package com.appuah.Routes

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import com.appuah.API
import com.appuah.DAO.SubjectDAO
import com.appuah.Models.SubjectsRequest
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

const val subjects = "$API/subjects"

@KtorExperimentalLocationsAPI
@Location(subjects)
class SubjectsRoute


@KtorExperimentalLocationsAPI
fun Route.subjects() {


    get<SubjectsRoute> {

        val user = call.receive<SubjectsRequest>()

        var professorSubjects : List<String> = emptyList()

        when (user.type) {
            "professor" -> professorSubjects=SubjectDAO().getProfessorSubjects(user.username)
            "student" -> SubjectDAO().getStudentSubjects(user.username)

        }

        val gson = Gson()
        call.respond(HttpStatusCode.Accepted ,gson.toJson(professorSubjects))

    }
}