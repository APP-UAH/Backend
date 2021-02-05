package routes

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import com.appuah.API
import com.appuah.Models.ReservationRequest
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.Route
import org.jetbrains.exposed.sql.appendTo
import java.time.LocalDateTime
import java.util.*
import kotlin.Exception

const val reservation = "$API/reservations"
const val createReservation = "$reservation/create"
const val reservationUser = "$reservation/user"
const val reservationPending = "$reservation/pending"
const val reservationUpdate = "$reservation/update"
const val reservationDelete = "$reservation/delete"

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
@Location(reservationPending)
class GetPendingReservation

@KtorExperimentalLocationsAPI
@Location(reservationUpdate)
class UpdateReservation

@KtorExperimentalLocationsAPI
@Location(reservationDelete)
class DeleteReservation

@KtorExperimentalLocationsAPI
fun Route.reservation(mediatorBehaviour: BehavioralMediator, mediatorCreation: CreationMediator) {
    get<ReservationsRoute> {
        val reservas = mediatorBehaviour.getAllReservationsFromDB()
        val jsonString = Gson().toJson(reservas)
        call.respondText(jsonString, contentType = Json)
    }

    post<GetReservationByUsername> {
        val reservaRequest = call.receive<ReservationRequest>()
        var reservas = mediatorBehaviour.getReservationFromUsername(reservaRequest.username)
        reservas = reservas + mediatorBehaviour.getReservationForStudents(reservaRequest.username)
        val jsonString = Gson().toJson(reservas)
        call.respondText(jsonString, contentType = Json)
    }

    get<GetPendingReservation> {
        val reservas = mediatorBehaviour.getPendingReservation()
        val jsonString = Gson().toJson(reservas)
        call.respondText(jsonString, contentType = Json)
    }

    post<CreateReservationRoute> {
        val reservaRequest = call.receive<ReservationRequest>()
        val newUUID = UUID.randomUUID().toString()
        try {
            val reserva = mediatorCreation.createReserva(
                reservaRequest.type,
                newUUID,
                null,
                LocalDateTime.parse(reservaRequest.begin),
                LocalDateTime.parse(reservaRequest.end),
                mediatorBehaviour.getRoom(reservaRequest.room_name)!!
            )
            mediatorBehaviour.addReservationToDB(reserva, reservaRequest.type, reservaRequest.username)
            mediatorBehaviour.addUserReservationToDB(reserva.id, reservaRequest.username)
            if (!reservaRequest.type.toLowerCase().equals("library")) {
                mediatorBehaviour.addEventReservationToDB(newUUID)
            } else if (!reservaRequest.type.toLowerCase().equals("events")) {
                val event_id = mediatorBehaviour.getEventIdFromReservationId(newUUID)
                mediatorBehaviour.addEventsSubjectToDB(
                    event_id!!,
                    reservaRequest.id_Subject,
                    reservaRequest.plan_Subject
                )
            }
            call.respondText("La reserva se ha creado correctamente")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e)
        }
    }

    patch<UpdateReservation> {
        val reservaRequest = call.receive<ReservationRequest>()
        try {
            val newReserva = mediatorCreation.createReserva(
                "Library",
                reservaRequest.id,
                reservaRequest.state,
                LocalDateTime.parse(reservaRequest.begin),
                LocalDateTime.parse(reservaRequest.end),
                mediatorBehaviour.getRoom(reservaRequest.room_name)!!
            )
            var reserva = mediatorBehaviour.getReservationFromId(reservaRequest.id)
            if (reserva?.id.isNullOrEmpty()) {
                call.respondText("La reserva no existe", contentType = ContentType.Text.Plain)
            } else {
                mediatorBehaviour.updateReservation(newReserva)
                call.respondText("La reserva ha sido actualizada", contentType = ContentType.Text.Plain)
            }

        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e)
        }
    }

    delete<DeleteReservation> {
        val reservaRequest = call.receive<ReservationRequest>()
        mediatorBehaviour.deleteReservationFromDB(reservaRequest.id)
        call.respondText("Reserva borrada", contentType = ContentType.Text.Plain)
    }

}