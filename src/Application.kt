package com.appuah


import com.appuah.database.DatabaseFactory
import com.appuah.database.DatabaseInstance
import com.appuah.routes.users
import com.appuah.routes.events
import com.appuah.routes.login
import com.appuah.routes.signUp
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.sessions.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val server: NettyApplicationEngine = embeddedServer(Netty, port = 8080) {

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
            //añadir autenticación
        }

        install(ContentNegotiation) {
            gson {
            }
        }

        DatabaseFactory.init()
        val db = DatabaseFactory.createInstance()




        routing {

            users(db)

            login()

            signUp()

            events()

        }
    }
    server.start(wait = true)
}

const val API = "AppUah"

data class MySession(val count: Int = 0)

