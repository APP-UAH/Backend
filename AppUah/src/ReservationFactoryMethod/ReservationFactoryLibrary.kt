package ReservationFactoryMethod

import ReservationEntities.ReservationLibrary
import RoomEntities.RoomInterface
import State.ReservationState
import java.time.LocalDateTime

class ReservationFactoryLibrary : ReservationFactory() {

    override fun createReservation(id: String, state: ReservationState, begin: LocalDateTime, end: LocalDateTime, room: RoomInterface): ReservationLibrary {
        return ReservationLibrary(id, state, begin, end, room)
    }
}