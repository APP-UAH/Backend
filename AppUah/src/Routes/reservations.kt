package com.appuah.Routes

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import com.appuah.API
import com.appuah.Models.ReservationRequest
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import java.lang.Exception
import java.util.*

const val reservation = "$API/reservations"
const val createReservation = "$reservation/create"

@KtorExperimentalLocationsAPI
@Location(reservation)
class ReservationsRoute

@KtorExperimentalLocationsAPI
@Location(createReservation)
class CreateReservationRoute

@KtorExperimentalLocationsAPI
fun Route.reservation(mediatorBehaviour: BehavioralMediator, mediatorCreation: CreationMediator){
    get<ReservationsRoute>{
        val reservaRequest = call.receive<ReservationRequest>()
        mediatorBehaviour.getReservationFromDB(reservaRequest.id, reservaRequest.username)

    }

    post<CreateReservationRoute>{
        val reservaRequest = call.receive<ReservationRequest>()
        val newUUID = UUID.randomUUID()
        try {
            val reserva = mediatorCreation.createReserva(
                reservaRequest.type,
                newUUID.toString(),
                null,
                reservaRequest.begin,
                reservaRequest.end,
                mediatorBehaviour.getRoom(reservaRequest.room_name)!!
            )

            mediatorBehaviour.addReservationToDB(reserva, reservaRequest.type)
            call.respond("La reserva se ha creado correctamente")
        } catch (e: Exception){
            call.respond(e)
        }
    }
}