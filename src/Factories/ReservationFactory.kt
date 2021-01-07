package com.appuah.Factories

import com.appuah.models.Reservation

open abstract class ReservationFactory {

    open fun updateReservation(reservationData: Reservation) {

        val reservation: Reservation = createReservation()
        reservation.updateReservationInDDBB(reservationData)
    }


    abstract fun createReservation(): Reservation

}