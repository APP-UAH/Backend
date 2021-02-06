package ReservationEntities

import RoomEntities.RoomInterface
import State.ReservationState
import java.time.LocalDateTime

interface ReservationInterface {
    var id: String
    var state : ReservationState
    var begin: LocalDateTime
    var end: LocalDateTime
    var room: RoomInterface
}