package Mediator

import Adapter.Adapter
import Builder.User
import Builder.UserBuilder
import ReservationEntities.ReservationInterface
import ReservationFactoryMethod.ReservationFactory
import RoomEntities.RoomInterface
import RoomFactoryMethod.RoomFactory
import RoomFactoryMethod.RoomType
import java.time.LocalDateTime

class CreationMediator : CreationMediatorInterface {

    private val factoryLibrary = ReservationFactory.getFactory("Library") //Factory Method de reservas
    private val factoryEvents = ReservationFactory.getFactory("Events") //Factory Method de reservas
    private val factorySubject = ReservationFactory.getFactory("Subject") //Factory Method de reservas
    private val FactoryLibraryClass = RoomFactory.getFactory(RoomType.LIBRARY)
    private val FactoryClass = RoomFactory.getFactory(RoomType.CLASSROOM)
    val adapter = Adapter()

    override fun createReserva(condition: String, id: String, state: Boolean?, begin: LocalDateTime, end: LocalDateTime, room: RoomInterface): ReservationInterface {
        return when (condition.toLowerCase()) {
            "library" -> factoryLibrary!!.createReservation(id, adapter.adaptBooleanToState(state), begin, end, room)
            "events" -> factoryEvents!!.createReservation(id, adapter.adaptBooleanToState(state), begin, end, room)
            "subject" -> factorySubject!!.createReservation(id, adapter.adaptBooleanToState(state), begin, end, room)
            else -> throw Exception("Invalid document type")
        }
    }


    override fun createRoom(condition: String, name: String, capacity: Int): RoomInterface {
        return when (condition.toLowerCase()) {
            "library" -> FactoryLibraryClass!!.createRoom(name, capacity)
            "events" -> FactoryClass!!.createRoom(name, capacity)
            "subject" -> FactoryClass!!.createRoom(name, capacity)
            else -> throw Exception("Invalid document type")
        }
    }

    override fun buildUser(
            username: String,
            password: String,
            type: Int,
            name: String,
            surname: String,
            phone_number:String,
            email: String,
            office: String,
            isDeputy: Boolean,
            isAssociated: Boolean
    ): User {
        return when (type) {
            0 -> UserBuilder.buildStudent(username, password, type, name, surname, email, isDeputy)
            1 -> UserBuilder.buildProfessor(username, password, type, name, surname, phone_number,email, office, isAssociated)
            2 -> UserBuilder.buildAdmin(username, password, type)
            else -> throw Exception("Invalid document type")
        }
    }
}