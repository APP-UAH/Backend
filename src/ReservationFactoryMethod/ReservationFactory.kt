package ReservationFactoryMethod

import ReservationEntities.ReservationInterface
import RoomEntities.RoomInterface
import State.ReservationState
import java.time.LocalDateTime

abstract class ReservationFactory {

    companion object {
        fun getFactory(condition: String?): ReservationFactory? {
            return when (condition) {
                "Library" -> ReservationFactoryLibrary()
                "Events" -> ReservationFactoryEvents()
                "Subject" -> ReservationFactorySubject()
                else -> throw Exception("Invalid document type")
            }
        }
    }

    abstract fun createReservation(id: String, state: ReservationState, begin: LocalDateTime, end: LocalDateTime, room: RoomInterface): ReservationInterface
}