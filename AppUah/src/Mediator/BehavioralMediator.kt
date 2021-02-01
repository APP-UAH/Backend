package Mediator

import Builder.User
import DAO.ReservationDAO
import DAO.UserDAO
import Iterator.RoomListCollection
import ReservationEntities.ReservationInterface
import RoomEntities.ClassRoom
import RoomEntities.LibraryRoom
import RoomEntities.RoomInterface
import State.StateAccepted
import State.StateNotAccepted
import State.StateNotProcessed

class BehavioralMediator : BehavioralMediatorInterface {


    val collectionRooms = RoomListCollection()
    val iteratorRooms = collectionRooms.createIterator()
    val resDAO = ReservationDAO(this)
    val usDAO = UserDAO(this)

    override fun changeState(condition: String, reserva: ReservationInterface) {
        when (condition.toLowerCase()) {
            "accepted" -> reserva.state = StateAccepted()
            "not proccessed" -> reserva.state = StateNotProcessed()
            "not accepted" -> reserva.state = StateNotAccepted()
            else -> throw Exception("Invalid document type")
        }
    }

    override fun addRoom(room: RoomInterface) {
        collectionRooms.add(room)
    }

    override fun getRoom(name: String): RoomInterface? {
        var room : RoomInterface? = null
        while (iteratorRooms.hasNext()) {
            val tempRoom = iteratorRooms.next()
            if (tempRoom.name.equals(name)) {
                if (tempRoom::class.equals("ClassRoom")){
                    var room = ClassRoom(tempRoom.name, tempRoom.capacity)
                } else {
                    var room = LibraryRoom(tempRoom.name, tempRoom.capacity)
                }
            }
        }
        iteratorRooms.index = 0
        return room
    }

    fun addReservationToDB(reserva : ReservationInterface, condition : String) {
        resDAO.addReservation(reserva, condition)
    }

    fun getReservationFromDB(id : Int): ReservationInterface? {
        return resDAO.getReservation(id)
    }

    fun getAllReservationsFromDB(): List<ReservationInterface?> {
        return resDAO.getAllReservation()
    }

    fun updateReservationInDB(id : Int, state : Boolean?) {
        resDAO.updateReservation(id, state)
    }

    fun deleteReservationFromDB() {
        resDAO.deleteReservation()
    }

    fun addUser(user:User){
        when (user.type){
            0 -> usDAO.addStudent(user.username!!,user.password!!,user.type!!,
                user.name!!,user.surname!!,user.email!!, user.isDeputy!!)
            1 -> usDAO.addProfessor(user.username!!,user.password!!,user.type!!,
                user.name!!,user.surname!!,user.phone_number!!,user.email!!,
                user.office!!,user.isAssociated!!)
            2 -> usDAO.addAdmin(user.username!!,user.password!!,user.type!!)
        }
    }

    fun getUser(condition: String, username:String) : User?{
        return when (condition.toLowerCase()){
            "student" -> usDAO.getStudent(username)
            "professor" -> usDAO.getProfessor(username)
            "admin" -> usDAO.getAdmin(username)
            else -> null
        }
    }

    fun getAllRooms() : ArrayList<RoomInterface>{
        return collectionRooms.rooms
    }

}