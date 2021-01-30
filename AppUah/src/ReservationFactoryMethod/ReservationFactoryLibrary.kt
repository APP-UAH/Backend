package ReservationFactoryMethod

import ReservationEntities.ReservationLibrary
import RoomEntities.RoomInterface
import State.ReservationState

class ReservationFactoryLibrary : ReservationFactory() {

    override fun createReservation(id: String, state: ReservationState, begin: String, end: String, room: RoomInterface): ReservationLibrary {
        return ReservationLibrary(id, state, begin, end, room)
    }
}