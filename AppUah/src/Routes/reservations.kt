package com.appuah.Routes

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import com.appuah.API
import com.appuah.Models.ReservationRequest
import com.google.gson.Gson
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
const val reservationUser = "$reservation/user"

@KtorExperimentalLocationsAPI
@Location(reservation)
class ReservationsRoute

@KtorExperimentalLocationsAPI
@Location(createReservation)
class CreateReservationRoute

@KtorExperimentalLocationsAPI
@Location(reservationUser)
class GetReservationByUsername

@KtorExperimentalLocationsAPI
fun Route.reservation(mediatorBehaviour: BehavioralMediator, mediatorCreation: CreationMediator){
    get<ReservationsRoute>{
        val reservas = mediatorBehaviour.getAllReservationsFromDB()
        val jsonString = Gson().toJson(reservas)
        call.respond(HttpStatusCode.Accepted,jsonString)
    }

    get<GetReservationByUsername>{
        val reservaRequest = call.receive<ReservationRequest>()
        val reservas = mediatorBehaviour.getReservationFromUsername(reservaRequest.username)
        val jsonString = Gson().toJson(reservas)
        call.respond(HttpStatusCode.Accepted,jsonString)
    }

    post<CreateReservationRoute>{
        val reservaRequest = call.receive<ReservationRequest>()
        val newUUID = UUID.randomUUID().toString()
        try {
            val reserva = mediatorCreation.createReserva(
                reservaRequest.type,
                newUUID,
                null,
                reservaRequest.begin,
                reservaRequest.end,
                mediatorBehaviour.getRoom(reservaRequest.room_name)!!
            )
            mediatorBehaviour.addReservationToDB(reserva, reservaRequest.type, reservaRequest.username)
            mediatorBehaviour.addUserReservationToDB(newUUID, reservaRequest.username)
            if (!reservaRequest.type.toLowerCase().equals("library")){
                mediatorBehaviour.addEventReservationToDB(newUUID)
                if (!reservaRequest.type.toLowerCase().equals("events")){
                    val event_id = mediatorBehaviour.getEventIdFromReservationId(newUUID)
                    mediatorBehaviour.addEventsSubjectToDB(event_id!!, reservaRequest.id_Subject, reservaRequest.plan_Subject)
                }
            }

            call.respond("La reserva se ha creado correctamente")
        } catch (e: Exception){
            call.respond(e)
        }
    }
}