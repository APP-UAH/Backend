package com.appuah.routes

import com.appuah.API
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.respond
import io.ktor.routing.Route

const val signUp = "$API/sign-up"

@KtorExperimentalLocationsAPI
@Location(signUp)
class SignUpRoutes

@KtorExperimentalLocationsAPI
fun Route.signUp(){
    post<SignUpRoutes>{



    }
    /*
    * Json = {
    *   username: string,
    *   password: string,
    *   ...
    *   role: [admin,alumn,professor]
    *
    *
    *
    *
    * */
}
