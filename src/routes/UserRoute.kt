package com.appuah.routes

import com.appuah.API
import com.appuah.database.ProfessorInstance
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.Route

const val USERS="$API/users"
const val USER_GET="$USERS/get"
const val USER_SELECT_ALL="$USERS/select_all"
const val USER_CREATE="$USERS/signup"
const val USER_DELETE="$USERS/delete"
const val USER_UPDATE="$USERS/update"


@KtorExperimentalLocationsAPI
@Location(USER_GET)
class UserGetRoute

@KtorExperimentalLocationsAPI
@Location(USER_CREATE)
class UserCreateRoute

@KtorExperimentalLocationsAPI
@Location(USER_DELETE)
class UserDeleteRoute

@KtorExperimentalLocationsAPI
@Location(USER_UPDATE)
class UserUpdateRoute

@KtorExperimentalLocationsAPI
@Location(USER_SELECT_ALL)
class UserSelectAllRoute

@KtorExperimentalLocationsAPI
fun Route.users(db: ProfessorInstance){

    // sustituir por post - preguntar a dani sobre cómo funciona postman para poder probar esto

    get<UserGetRoute>{
        var answer = db.getProfessor("Fermin68")
        if (answer != null) {
            call.respond(HttpStatusCode.Accepted, answer.username)
        } else {
            call.respond(HttpStatusCode.BadRequest, "puta mierda")
        }

    }

    get<UserSelectAllRoute>{
        var answer = db.getAllProfessor()
        if (answer != null) {
            call.respond(HttpStatusCode.Accepted, answer)
        } else {
            call.respond(HttpStatusCode.BadRequest, "puta mierda")
        }
    }

    post<UserCreateRoute>{

        try {
            var answer = db.addProfessor("Fermin68","patrones", "Fermín","Trujillo",
            "626458344","fermin.trujillo@uah.es","N304")
            if (answer != null) {
                call.respond(HttpStatusCode.Created, answer.username)
            }
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