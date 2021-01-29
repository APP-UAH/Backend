package RoomEntities

class ClassRoom(override var name: String, override var capacity: Int): RoomInterface {

    override fun whereIs():String? {
        var zone =""
        var type=""
        var number='0'
        //val name = this.name.toCharArray()
        when (this.name.toCharArray()[0]) {
            'N' -> zone = "Norte"
            'S' -> zone = "Sur"
            'E' -> zone = "Este"
            'O' -> zone = "Oeste"
        }
        when (this.name.toCharArray()[1]) {
            'A' -> type = "Aula"
            'L' -> type = "Laboratorio"
            else -> type = "Otro"
        }
        number = this.name.toCharArray()[2]


        return "El $type se encuentra en el $zone con numero $number"
    }

}