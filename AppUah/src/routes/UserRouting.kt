package routes

import Mediator.BehavioralMediator

import com.appuah.API
import Models.LoginRequest
import Models.LoginResponse
import Models.UserRequest
import Models.UserResponse
import com.appuah.Models.AllUsersResponse
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.Route

const val UserRoute = "$API/adduser"
const val AllUsers = "$API/getAllUser"

@KtorExperimentalLocationsAPI
@Location(UserRoute)
class doUser

@KtorExperimentalLocationsAPI
@Location(AllUsers)
class allUser


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
            call.respond(HttpStatusCode.Created, gson.toJson(UserResponse(true)))
        } catch (e: Exception){
            call.respond(HttpStatusCode.InternalServerError, e)
        }
    }
    get<allUser>{
        try{
            val allProfessors = mediator.usDAO.getAllProfessors()
            val allStudents = mediator.usDAO.getAllStudents()
            call.respond(gson.toJson(AllUsersResponse(allStudents,allProfessors)))

        }catch (e: Exception){
            call.respond(e)
        }
    }

}