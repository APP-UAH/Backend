package com.appuah.models

interface Reservation {
    var id: Int
    var accepted: Boolean
    var begin: String
    var end: String

    fun isAccepted(): Boolean
    fun updateReservationInDDBB(reservationData: Reservation)

}