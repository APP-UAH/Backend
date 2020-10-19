package com.appuah

import com.appuah.API
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.application.log
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set

const val reservas = "$API/reservas"

@KtorExperimentalLocationsAPI
@Location(reservas)
class ReservasRoute

@KtorExperimentalLocationsAPI
fun Route.reservas(){
    get<ReservasRoute>{
        call.respond(HttpStatusCode(200, "Accepted"),"Hello there!")
    }
}
