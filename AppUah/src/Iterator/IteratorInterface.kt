package Iterator

import RoomEntities.RoomInterface

interface IteratorInterface {
    fun hasNext(): Boolean
    fun next(): RoomInterface
}

