package com.appuah.ReservationFactoryMethod

import com.appuah.Factories.ReservationFactory
import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationEntities.ReservationSubject

class ReservationFactorySubject : ReservationFactory(){

    override fun createReservation(): Reservation {
        return ReservationSubject()
    }
}