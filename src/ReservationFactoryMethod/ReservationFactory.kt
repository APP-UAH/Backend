package com.appuah.Factories

import com.appuah.ReservationEntities.Reservation

open abstract class ReservationFactory {

    abstract fun createReservation(): Reservation

}