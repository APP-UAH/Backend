package ReservationFactoryMethod

import ReservationEntities.ReservationSubject
import RoomEntities.RoomInterface
import State.ReservationState
import java.time.LocalDateTime

class ReservationFactorySubject : ReservationFactory(){

    override fun createReservation(id: String, state: ReservationState, begin: LocalDateTime, end: LocalDateTime, room: RoomInterface): ReservationSubject {
        return ReservationSubject(id, state, begin, end, room)
    }
}