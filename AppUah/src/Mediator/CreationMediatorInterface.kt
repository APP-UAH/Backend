package Mediator

import Builder.User
import ReservationEntities.ReservationInterface
import RoomEntities.RoomInterface
import jdk.vm.ci.meta.Local
import java.time.LocalDateTime

interface CreationMediatorInterface {

    fun createReserva(condition : String, id : String, state : Boolean?, begin : LocalDateTime, end : LocalDateTime, room: RoomInterface) : ReservationInterface;

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