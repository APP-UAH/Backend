package com.appuah.ReservationFactoryMethod

import com.appuah.Factories.ReservationFactory
import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationEntities.ReservationEvents

class ReservationFactoryEvents : ReservationFactory() {

    override fun createReservation(): Reservation {
        return ReservationEvents()
    }
}