package ReservationEntities

import RoomEntities.RoomInterface
import State.ReservationState
import java.time.LocalDateTime

class ReservationEvents(override var id: String,
                        override var state: ReservationState,
                        override var begin: LocalDateTime,
                        override var end: LocalDateTime,
                        override var room: RoomInterface): ReservationInterface {

    override fun toString(): String {
        return "ReservationEvents(id=$id, state=${state.state}, begin='$begin', end='$end', room='${room.whereIs()})"
    }
}