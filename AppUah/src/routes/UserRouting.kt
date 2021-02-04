
package routes

import Mediator.BehavioralMediator

import com.appuah.API
import Models.LoginRequest
import Models.LoginResponse
import Models.UserRequest
import Models.UserResponse
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.Route

const val UserRoute = "$API/adduser"
const val getUser = "$API/get-all-users"

@KtorExperimentalLocationsAPI
@Location(UserRoute)
class doUser

@KtorExperimentalLocationsAPI
@Location(getUser)
class getAllUser

@KtorExperimentalLocationsAPI
fun Route.User(mediator: BehavioralMediator){
    var gson = Gson()
    post<doUser>{ 
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
            call.respondText(gson.toJson(true),contentType = Json)
        } catch (e: Exception){
            call.respond(HttpStatusCode.InternalServerError, e)
        }
   }

    get<getAllUser>{
        call.respondText(Gson().toJson(UserResponse(mediator.getAllStudents(), mediator.getAllProfessors())),contentType = Json)
    }
}