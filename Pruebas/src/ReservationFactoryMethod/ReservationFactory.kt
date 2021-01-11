package com.appuah.Factories

import com.appuah.ReservationEntities.Reservation
import com.appuah.ReservationFactoryMethod.ReservationFactoryEvents

open abstract class ReservationFactory {

    companion object {
        fun getReservation(condition: String?): ReservationFactory? {
            return when (condition) {
                "Library" -> ReservationFactoryLibrary()
                "Events" -> ReservationFactoryEvents()
                else -> throw Exception("Invalid document type")
            }
        }
    }

    abstract fun createReservation(id: Int, accepted: Boolean, begin: String, end: String): Reservation

}