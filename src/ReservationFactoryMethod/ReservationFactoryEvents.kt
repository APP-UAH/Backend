package ReservationFactoryMethod

import ReservationEntities.ReservationEvents
import RoomEntities.RoomInterface
import State.ReservationState
import java.time.LocalDateTime

class ReservationFactoryEvents : ReservationFactory() {

    override fun createReservation(id: String, state: ReservationState, begin: LocalDateTime, end: LocalDateTime, room: RoomInterface): ReservationEvents {
        return ReservationEvents(id, state, begin, end, room)
    }
}