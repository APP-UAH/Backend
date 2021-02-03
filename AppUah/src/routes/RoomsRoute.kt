package routes

import Mediator.BehavioralMediator
import com.appuah.API
import com.appuah.Models.RoomRequest
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import java.time.LocalDateTime

const val ClassRooms = "$API/Classrooms"
const val LibraryRooms = "$API/Libraryrooms"

@KtorExperimentalLocationsAPI
@Location(ClassRooms)
class GetAllClassRooms

@KtorExperimentalLocationsAPI
@Location(LibraryRooms)
class GetAllLibraryRooms

@KtorExperimentalLocationsAPI
fun Route.rooms(mediatorBehaviour: BehavioralMediator) {
    get<GetAllClassRooms>{
        var roomRequest = call.receive<RoomRequest>()
        call.respond(HttpStatusCode.OK,  Gson().toJson(mediatorBehaviour.getAllRoomBetweenDate(LocalDateTime.parse(roomRequest.begin), LocalDateTime.parse(roomRequest.end), "Classroom")))
    }

    get<GetAllLibraryRooms>{
        var roomRequest = call.receive<RoomRequest>()
        call.respond(HttpStatusCode.OK,  Gson().toJson(mediatorBehaviour.getAllRoomBetweenDate(LocalDateTime.parse(roomRequest.begin), LocalDateTime.parse(roomRequest.end), "Library")))
    }
}