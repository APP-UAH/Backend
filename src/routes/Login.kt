package routes

import Mediator.BehavioralMediator

import com.appuah.API
import Models.LoginRequest
import Models.LoginResponse
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.Route

const val login = "$API/login"

@KtorExperimentalLocationsAPI
@Location(login)
class doLogin

@KtorExperimentalLocationsAPI
fun Route.login(mediatorBehaviour: BehavioralMediator) {
    post<doLogin>{
        try {
            val loginRequest = call.receive<LoginRequest>()
            val newLoginRequest = mediatorBehaviour.usDAO.getUser(loginRequest.username)
            var gson = Gson()
            if (newLoginRequest != null) {
                if(!newLoginRequest.password.equals(loginRequest.password)){
                    var responseJSon = gson.toJson(LoginResponse(false, null))
                    call.respond(HttpStatusCode.NotAcceptable, responseJSon)
                }else {
                    var responseJSon = gson.toJson(LoginResponse(true, newLoginRequest.type))
                    call.respond(HttpStatusCode.OK, responseJSon)
                }
            }
        }catch (e: Exception){
            call.respond(e)
        }
    }
}
