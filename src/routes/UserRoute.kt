package com.appuah.routes

import com.appuah.API
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.respond
import io.ktor.routing.Route

const val USERS="$API/users"
const val USER_LOGIN="$USERS/login"


@KtorExperimentalLocationsAPI
@Location(USER_LOGIN)
class UserLoginRoute

@KtorExperimentalLocationsAPI
fun Route.users(){

    post<UserLoginRoute>{
        call.respond(HttpStatusCode(200, "Accepted"),"Hello there!")
    }

}