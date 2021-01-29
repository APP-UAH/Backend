package ReservationFactoryMethod

import ReservationEntities.ReservationSubject
import RoomEntities.RoomInterface
import State.ReservationState

class ReservationFactorySubject : ReservationFactory(){

    override fun createReservation(id: Int, state: ReservationState, begin: String, end: String, room: RoomInterface): ReservationSubject {
        return ReservationSubject(id, state, begin, end, room)
    }
}