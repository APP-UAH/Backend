package RoomFactoryMethod

import RoomEntities.LibraryRoom
import RoomEntities.RoomInterface

class LibraryRoomFactory : RoomFactory() {

    override fun createRoom(name: String, capacity: Int): RoomInterface {
        return LibraryRoom(name, capacity)
    }
}