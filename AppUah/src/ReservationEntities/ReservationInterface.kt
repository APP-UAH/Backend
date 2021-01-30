package ReservationEntities

import RoomEntities.RoomInterface
import State.ReservationState

interface ReservationInterface {
    var id: Int
    var state : ReservationState
    var begin: String
    var end: String
    var room: RoomInterface
}