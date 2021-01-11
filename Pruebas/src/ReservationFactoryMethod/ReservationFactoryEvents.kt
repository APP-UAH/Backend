package com.appuah.ReservationFactoryMethod

import com.appuah.Factories.ReservationFactory
import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationEntities.ReservationEvents

class ReservationFactoryEvents : ReservationFactory() {

    override fun createReservation(id: Int, accepted: Boolean, begin: String, end: String): Reservation {
        return ReservationEvents(id, accepted, begin, end)
    }
}