package Mediator

import RoomEntities.RoomInterface

interface BehavioralMediatorInterface {

    fun addRoom(room : RoomInterface)

    fun getRoom(name : String) : RoomInterface?;

}