package com.appuah.Routes

import com.appuah.API
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route

const val reservation = "$API/reservations"

@KtorExperimentalLocationsAPI
@Location(reservation)
class ReservationsRoute

@KtorExperimentalLocationsAPI
fun Route.reservation(){
    get<ReservationsRoute>{
        call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
    }
}