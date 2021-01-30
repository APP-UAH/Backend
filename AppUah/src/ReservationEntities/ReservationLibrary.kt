package ReservationEntities

import RoomEntities.RoomInterface
import State.ReservationState

class ReservationLibrary(override var id: String,
                         override var state: ReservationState,
                         override var begin: String,
                         override var end: String,
                         override var room: RoomInterface): ReservationInterface {

    override fun toString(): String {
        return "ReservationLibrary(id=$id, state=${state.state}, begin='$begin', end='$end', room='${room.whereIs()})"
    }
}