package RoomFactoryMethod

import RoomEntities.ClassRoom
import RoomEntities.RoomInterface

class ClassRoomFactory : RoomFactory() {

    override fun createRoom(name: String, capacity: Int): RoomInterface {
        return ClassRoom(name, capacity)
    }
}