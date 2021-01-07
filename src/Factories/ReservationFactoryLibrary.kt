package com.appuah.Factories

import com.appuah.models.Reservation
import com.appuah.models.ReservationLibrary

class ReservationFactoryLibrary : ReservationFactory() {


    override fun createReservation(): Reservation {
        return ReservationLibrary()
    }
}