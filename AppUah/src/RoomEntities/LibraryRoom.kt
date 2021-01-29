package RoomEntities

class LibraryRoom(override var name: String, override var capacity: Int): RoomInterface {
    override fun whereIs():String? {
    var ponunnombreahi = if(this.name.equals("Pequeña"))" es la de la izq." else " es la de la derecha."
        return "La sala ${this.name} de capacidad ${this.capacity} $ponunnombreahi"
    }

}