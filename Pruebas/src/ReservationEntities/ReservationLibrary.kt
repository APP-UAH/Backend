package com.appuah.ReservationEntities

class ReservationLibrary(override var id: Int, override var accepted: Boolean, override var begin: String, override var end: String): Reservation {

    override fun isAccepted(): Boolean {
        return accepted
    }

    override fun toString(): String {
        return "ReservationLibrary(id=$id, accepted=$accepted, begin='$begin', end='$end')"
    }

}