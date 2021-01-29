package Mediator

import Builder.User
import ReservationEntities.ReservationInterface
import RoomEntities.RoomInterface

interface CreationMediatorInterface {

    fun createReserva(condition : String, id : Int, state : Boolean?, begin : String, end : String, room: RoomInterface) : ReservationInterface;

    fun createRoom(condition : String, name : String, capacity : Int) : RoomInterface;

    fun buildUser(username: String,
                   password: String,
                   type: Int,
                   name: String,
                   surname: String,
                   phone_number:String,
                   email: String,
                   office: String,
                   isDeputy: Boolean,
                   isAssociated: Boolean) : User;

}