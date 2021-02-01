package com.appuah

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import Routes.User
import Singleton.DatabaseSingleton
import Routes.login
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.features.*
import io.ktor.gson.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Locations) {
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        gson {
        }
    }
    DatabaseSingleton.init()
    val mediatorBehavior = BehavioralMediator()
    val mediatorCreation = CreationMediator()
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        login(mediatorBehavior)
        User(mediatorBehavior)
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

const val API = "/AppUah"

