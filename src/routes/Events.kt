package com.appuah.routes

import com.appuah.API
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.respond
import io.ktor.routing.Route

const val events = "$API/events"

@KtorExperimentalLocationsAPI
@Location(events)
class EventsRoute

@KtorExperimentalLocationsAPI
fun Route.events(){
    get<EventsRoute>{

        //Cogemos un evento

        call.respond(HttpStatusCode(200, "Accepted"),"Hello there!")
    }

    post<EventsRoute>{

        //insertamos un evento

    }

    put<EventsRoute>{

        //Actualizamos los datos de un Evento

    }

    delete<EventsRoute>{

        //Eliminamos un Evento

    }

}
