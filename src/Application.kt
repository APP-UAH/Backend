package com.appuah

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import routes.*
import Singleton.DatabaseSingleton
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.features.*
import io.ktor.gson.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(Locations) {
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        allowNonSimpleContentTypes = true
        anyHost()
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    DatabaseSingleton.init()
    val mediatorBehavior = BehavioralMediator()
    val mediatorCreation = CreationMediator()
    init(mediatorCreation, mediatorBehavior)

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
        subjects()
        reservation(mediatorBehavior, mediatorCreation)
        login(mediatorBehavior)
        User(mediatorBehavior)
        rooms(mediatorBehavior)
    }
}

const val API = "/AppUah"

fun init(mediatorCreation: CreationMediator, mediatorBehavioral: BehavioralMediator) {
    var clase = mediatorCreation.createRoom("Subject", "NA8", 124)
    mediatorBehavioral.addRoom(clase)
    clase = mediatorCreation.createRoom("Subject", "SA8", 75)
    mediatorBehavioral.addRoom(clase)
    clase = mediatorCreation.createRoom("Library", "pequenia", 2)
    mediatorBehavioral.addRoom(clase)
    clase = mediatorCreation.createRoom("Library", "Grande", 6)
    mediatorBehavioral.addRoom(clase)
}
