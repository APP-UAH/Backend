package com.appuah.Factories

import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationEntities.ReservationLibrary

class ReservationFactoryLibrary : ReservationFactory() {

    override fun createReservation(id: Int, accepted: Boolean, begin: String, end: String): Reservation {
        return ReservationLibrary(id, accepted, begin, end)
    }
}