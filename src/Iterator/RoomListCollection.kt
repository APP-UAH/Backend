package Iterator

import RoomEntities.RoomInterface

class RoomListCollection {
    val rooms = ArrayList<RoomInterface>()

    fun add(room: RoomInterface) {
        rooms.add(room)
    }

    fun createIterator() = ListIterator(this)

    class ListIterator(private val collection: RoomListCollection) : IteratorInterface {

        var index = 0

        override fun next(): RoomInterface {
            return collection.rooms[index++]
        }

        override fun hasNext(): Boolean {
            return index < collection.rooms.size
        }

    }
}