package com.appuah.routes

import com.appuah.API
import com.appuah.database.ProfessorInstance
import com.appuah.entities.LoginRequest
import com.appuah.entities.LoginResponse
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.routing.Route

const val login = "$API/login"

@KtorExperimentalLocationsAPI
@Location(login)
class LoginRoute

@KtorExperimentalLocationsAPI
fun Route.login(db: ProfessorInstance){
    post<LoginRoute>{
        val loginRequest = call.receive<LoginRequest>()

        val professor = db.getProfessor(loginRequest.username)
        if (professor != null) {
            if (professor.password == loginRequest.password) {
                call.respond(
                        LoginResponse(true, "Login successful!")
                )
            } else {
                call.respond(
                        LoginResponse(false, "Invalid password!")
                )
            }
        } else {
            call.respond(
                    LoginResponse(false, "Invalid username")
            )
        }

    }
}


