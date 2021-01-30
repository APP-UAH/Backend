package ReservationEntities

import RoomEntities.RoomInterface
import State.ReservationState

class ReservationSubject(override var id: Int,
                         override var state: ReservationState,
                         override var begin: String,
                         override var end: String,
                         override var room: RoomInterface) : ReservationInterface {

    override fun toString(): String {
        return "ReservationSubject(id=$id, state=${state.state}, begin='$begin', end='$end', room='${room.whereIs()}')"
    }
}