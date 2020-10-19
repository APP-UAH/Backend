package com.appuah

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.sessions.*
import io.ktor.features.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val server : NettyApplicationEngine = embeddedServer(Netty, port = 8080) {
        install(Locations) {
        }

        install(Sessions) {
            cookie<MySession>("MY_SESSION") {
                cookie.extensions["SameSite"] = "lax"
            }
        }

        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            allowCredentials = true
            anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
        }

        install(Authentication) {
        }

        install(ContentNegotiation) {
            gson {
            }
        }

        routing {
            get("/") {
                call.respond(HttpStatusCode(200, "Accepted"), "Hello world!")
            }

            post("/login") {

            }

            route("/hoja-de-firmas") {

                get {

                }

                patch {

                }

            }

            route("/reservas") {

                get {

                }

                post {

                }

                patch {

                }

                delete {

                }

            }

            reservas()

        }
    }
    server.start(wait = true)
}

const val API = "/app-uah"

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}") data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}

data class MySession(val count: Int = 0)

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

