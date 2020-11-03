package com.appuah.routes

import com.appuah.API
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.respond
import io.ktor.routing.Route

const val login = "$API/login"

@KtorExperimentalLocationsAPI
@Location(login)
class LoginRoute

@KtorExperimentalLocationsAPI
fun Route.login(){
    post<LoginRoute>{

    }
}


