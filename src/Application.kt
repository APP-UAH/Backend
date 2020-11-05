package com.appuah

<<<<<<< HEAD
import com.appuah.routes.users
=======
import com.appuah.routes.events
import com.appuah.routes.login
import com.appuah.routes.signUp
>>>>>>> cbd703dc5640c251fffc8542e1ecf339110a1058
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.sessions.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*
import io.ktor.http.content.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

<<<<<<< HEAD
    val server: NettyApplicationEngine = embeddedServer(Netty, port = 8080) {
=======
    val server : NettyApplicationEngine = embeddedServer(Netty, port = 8080) {
>>>>>>> cbd703dc5640c251fffc8542e1ecf339110a1058
        install(Locations) {
        }
        install(Sessions) {
            cookie<MySession>("MY_SESSION") {
                cookie.extensions["SameSite"] = "lax"
            }
        }


        // http://localhost:8080/AppUah/users/login
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            allowCredentials = true
            anyHost()
        }


        install(Authentication) {
        }

        install(ContentNegotiation) {
            gson {
            }
        }

        routing {
<<<<<<< HEAD

            users()
=======

            login()
            
            signUp()

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

            events()
>>>>>>> cbd703dc5640c251fffc8542e1ecf339110a1058

        }
    }
    server.start(wait = true)
}

const val API = "AppUah"

data class MySession(val count: Int = 0)

