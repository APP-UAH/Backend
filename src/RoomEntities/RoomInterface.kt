package RoomEntities

interface RoomInterface{
    val name: String
    val capacity: Int

    fun whereIs():String?

}