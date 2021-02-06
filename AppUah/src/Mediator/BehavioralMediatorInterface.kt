package Mediator

import ReservationEntities.ReservationInterface
import RoomEntities.RoomInterface

interface BehavioralMediatorInterface {

    fun changeState(condition : String, reserva : ReservationInterface);

    fun addRoom(room : RoomInterface)

    fun getRoom(name : String) : RoomInterface?;

}