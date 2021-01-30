package ReservationFactoryMethod

import ReservationEntities.ReservationEvents
import RoomEntities.RoomInterface
import State.ReservationState

class ReservationFactoryEvents : ReservationFactory() {

    override fun createReservation(id: String, state: ReservationState, begin: String, end: String, room: RoomInterface): ReservationEvents {
        return ReservationEvents(id, state, begin, end, room)
    }
}