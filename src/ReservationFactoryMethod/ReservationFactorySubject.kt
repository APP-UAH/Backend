package com.appuah.ReservationFactoryMethod

import com.appuah.Factories.ReservationFactory

class ReservationFactorySubject : ReservationFactory(){

    override fun createReservation(id: Int, accepted: Boolean, begin: String, end: String): Reservation {
        return ReservationSubject(id, accepted, begin, end)
    }
}