package RoomFactoryMethod

import RoomEntities.RoomInterface

abstract class RoomFactory{
    companion object {
        fun getFactory(type:RoomType):RoomFactory?{
            return when (type){
                RoomType.LIBRARY->LibraryRoomFactory()
                RoomType.CLASSROOM->ClassRoomFactory()
                else -> null
            }
        }
    }
    abstract fun createRoom(name:String, capacity:Int): RoomInterface
}