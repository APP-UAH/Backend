package com.appuah.routes

import com.appuah.API
import com.appuah.database.DatabaseInterface
import com.appuah.database.TodoDatabaseInterface
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.Route
import io.ktor.sessions.*

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
    db: DatabaseInterface
){

    // sustituir por post - preguntar a dani sobre cómo funciona postman para poder probar esto
    post<UserLoginRoute>{

    }

    post<UserCreateRoute>{


        try {
            answer = db.addProfessor("Fermin68","patrones", "Fermín","Trujillo",
            "626458344","fermin.trujillo@uah.es","N304")
            call.respond(HttpStatusCode.Created, answer)
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