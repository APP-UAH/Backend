package com.appuah.Factories

import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationEntities.ReservationLibrary

class ReservationFactoryLibrary : ReservationFactory() {


    override fun createReservation(): Reservation {
        return ReservationLibrary()
    }
}