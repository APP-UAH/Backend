package com.appuah.ReservationEntities

class ReservationEvents(override var id: Int, override var accepted: Boolean, override var begin: String, override var end: String): Reservation {

    override fun isAccepted(): Boolean {
        return accepted
    }

    override fun toString(): String {
        return "ReservationEvents(id=$id, accepted=$accepted, begin='$begin', end='$end')"
    }


}