package com.appuah.ReservationFactoryMethod

import com.appuah.Factories.ReservationFactory
import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationEntities.ReservationSubject

class ReservationFactorySubject : ReservationFactory(){

    override fun createReservation(id: Int, accepted: Boolean, begin: String, end: String): Reservation {
        return ReservationSubject(id, accepted, begin, end)
    }
}