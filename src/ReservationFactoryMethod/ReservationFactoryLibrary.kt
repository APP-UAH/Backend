package com.appuah.Factories

class ReservationFactoryLibrary : ReservationFactory() {

    override fun createReservation(id: Int, accepted: Boolean, begin: String, end: String): Reservation {
        return ReservationLibrary(id, accepted, begin, end)
    }
}