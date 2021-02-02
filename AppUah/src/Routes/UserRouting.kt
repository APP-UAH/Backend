package Routes

import Mediator.BehavioralMediator

import com.appuah.API
import Models.LoginRequest
import Models.LoginResponse
import Models.UserRequest
import Models.UserResponse
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.Route

const val UserRoute = "$API/adduser"

@KtorExperimentalLocationsAPI
@Location(UserRoute)
class doUser

@KtorExperimentalLocationsAPI
fun Route.User(mediator: BehavioralMediator){
    post<doUser>{
        var gson = Gson()
        try {
            val userRequest = call.receive<UserRequest>()
            when(userRequest.type){
                0-> mediator.usDAO.addStudent(userRequest.username,userRequest.password,userRequest.type,
                    userRequest.name,userRequest.surname,userRequest.email,userRequest.is_deputy!!)

                1-> mediator.usDAO.addProfessor(userRequest.username,userRequest.password,userRequest.type,
                    userRequest.name,userRequest.surname,userRequest.phone_number!!,userRequest.email,
                    userRequest.office!!,userRequest.is_associated!!)

                2-> mediator.usDAO.addAdmin(userRequest.username,userRequest.password,userRequest.type)
            }
            call.respond(HttpStatusCode.Created, gson.toJson(UserResponse(true)))
        } catch (e: Exception){
            call.respond(HttpStatusCode.InternalServerError, e)
        }
   }

}