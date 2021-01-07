package com.appuah.routes

import com.appuah.API
import com.appuah.database.ProfessorInstance
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.Route

const val RESERVATION="$API/reservation"
const val SUBJECT="$RESERVATION/subject"
const val EVENT="$RESERVATION/event"
const val LIBRARY="$RESERVATION/library"


const val RESERVATION_GETALL="$RESERVATION/getall"
const val SUBJECT_GET="$SUBJECT/get"
const val EVENT_GET="$EVENT/get"
const val LIBRARY_GET="$LIBRARY/get"
const val SUBJECT_POST="$SUBJECT/post"
const val EVENT_POST="$EVENT/post"
const val LIBRARY_POST="$LIBRARY/post"
const val SUBJECT_DELETE="$SUBJECT/delete"
const val EVENT_DELETE="$EVENT/delete"
const val LIBRARY_DELETE="$LIBRARY/delete"


@KtorExperimentalLocationsAPI
@Location(RESERVATION_GETALL)
class ReservationGetAllRoute

@KtorExperimentalLocationsAPI
@Location(SUBJECT_GET)
class SubjectGetRoute

@KtorExperimentalLocationsAPI
@Location(EVENT_GET)
class EventGetRoute

@KtorExperimentalLocationsAPI
@Location(LIBRARY_GET)
class LibraryGetRoute

@KtorExperimentalLocationsAPI
@Location(SUBJECT_POST)
class SubjectPostRoute


//Distintos endpoint para los distintos tipos de reservas o un campo en el json¿?