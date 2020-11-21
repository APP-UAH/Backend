package com.appuah.routes

import com.appuah.API
import com.appuah.database.DatabaseInstance
import com.appuah.database.DatabaseInterface
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.Route

const val USERS="$API/users"
const val USER_LOGIN="$USERS/login"
const val USER_CREATE="$USERS/signup"


@KtorExperimentalLocationsAPI
@Location(USER_LOGIN)
class UserLoginRoute

@KtorExperimentalLocationsAPI
@Location(USER_CREATE)
class UserCreateRoute

@KtorExperimentalLocationsAPI
fun Route.users(
    db: DatabaseInstance
){

    // sustituir por post - preguntar a dani sobre cómo funciona postman para poder probar esto
    get<UserLoginRoute>{
        call.respond(HttpStatusCode(200, "Accepted"),"Hello there!")
    }

    post<UserCreateRoute>{


        try {
            db.addProfessor("Daniel","patrones", "Fermín","Trujillo",
            "626458344","fermin.trujillo@uah.es","N304")
            /*newUser?.userId?.let {
                call.sessions.set(MySession(it))
                call.respondText(jwtService.generateToken(newUser), status = HttpStatusCode.Created)
            }*/
        } catch (e: Throwable) {
            application.log.error("Failed to register user", e)
            call.respond(HttpStatusCode.BadRequest, "Problems creating User")
        }

    }

}