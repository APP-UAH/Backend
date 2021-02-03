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
import com.appuah.DAO.EventsReservationDAO
import com.appuah.DAO.EventsSubjectsDAO
import com.appuah.DAO.UserReservationDAO
import java.time.LocalDateTime

class BehavioralMediator : BehavioralMediatorInterface {


    val collectionRooms = RoomListCollection()
    val iteratorRooms = collectionRooms.createIterator()
    val resDAO = ReservationDAO(this)
    val usDAO = UserDAO(this)
    val userResDAO = UserReservationDAO()
    val eventsResDAO = EventsReservationDAO()
    val eventsSubDAO = EventsSubjectsDAO()

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
                room = tempRoom
            }
        }
        iteratorRooms.index = 0
        return room
    }

    fun getAllRoomBetweenDate(begin: LocalDateTime, end: LocalDateTime, condition: String): ArrayList<RoomInterface> {
        var rooms = resDAO.getAllRoomsFromDB(begin, end)
        val newCollector = RoomListCollection()
        newCollector.rooms.addAll(collectionRooms.rooms)
        val newIterator = newCollector.createIterator()
        if (condition.toLowerCase().equals("library")) {
            while (newIterator.hasNext()) {
                val tempRoom = newIterator.next()
                if (tempRoom is LibraryRoom) {
                    if (rooms.any { it?.name?.toLowerCase().equals(tempRoom.name.toLowerCase()) }) {
                        newCollector.rooms.remove(tempRoom)
                        newIterator.index = 0
                    }
                } else{
                    newCollector.rooms.remove(tempRoom)
                    newIterator.index = 0
                }
            }
        } else {
            while (newIterator.hasNext()) {
                val tempRoom = newIterator.next()
                if (tempRoom is ClassRoom) {
                    if (rooms.any { it?.name?.toLowerCase().equals(tempRoom.name.toLowerCase()) }) {
                        newCollector.rooms.remove(tempRoom)
                        newIterator.index = 0
                    }
                } else {
                    newCollector.rooms.remove(tempRoom)
                    newIterator.index = 0
                }
            }
        }
        return newCollector.rooms
    }

    fun addReservationToDB(reserva : ReservationInterface, condition : String, username: String) {
        resDAO.addReservation(reserva, condition, username)
    }

    fun addUserReservationToDB(id: String, username: String){
        userResDAO.addUserReservation(id, username)
    }

    fun addEventReservationToDB(id_res: String){
        eventsResDAO.addEventReservation(id_res)
    }

    fun addEventsSubjectToDB(id_event: Int, code: String, plan: String){
        eventsSubDAO.addEvenSubject(id_event, code, plan)
    }

    fun getEventIdFromReservationId(id_res: String): Int?{
        return eventsResDAO.getEvent(id_res)
    }

    fun getAllReservationsFromDB(): List<ReservationInterface?> {
        return resDAO.getAllReservation()
    }

    fun getReservationFromId(id: String): ReservationInterface?{
        return resDAO.getReservation(id)
    }

    fun getReservationFromUsername(username: String): List<ReservationInterface?>{
        return resDAO.getReservationByUsername(username)
    }

    fun getPendingReservation(): List<ReservationInterface?>{
        return resDAO.getPendingReservationFromDB()
    }

    fun deleteReservationFromDB(id: String) {
        resDAO.deleteReservation(id)
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

    fun updateReservation(reserva : ReservationInterface, condition: String){
        return resDAO.update(reserva, condition)
    }

    fun getAllProfessors(): List<User?> {
        return usDAO.getAllProfessors()
    }

    fun getAllStudents(): List<User?> {
        return usDAO.getAllStudents()
    }

}