package DAO

import Adapter.Adapter
import Mediator.BehavioralMediator
import Mediator.CreationMediator
import ReservationEntities.ReservationInterface
import Tables.Reservation
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

import java.time.LocalDateTime

class ReservationDAO {

    var mediatorBehaviour: BehavioralMediator
    val mediatorCreation = CreationMediator()
    val adapter = Adapter()

    constructor(mediator: BehavioralMediator) {
        this.mediatorBehaviour = mediator
    }

    fun addReservation(reserva : ReservationInterface, condition : String) {
        transaction {
            Reservation.insert {
                it[state] = adapter.adaptStateToBoolean(reserva.state)
                it[begin] = LocalDateTime.now()
                it[end] = LocalDateTime.now().plusDays(2)
                it[room] = reserva.room.name
                it[type] = condition
            }
        }
    }

    fun getReservation(id : Int): ReservationInterface? {
        return transaction {
            Reservation.select { Reservation.id.eq(id) }.map { rowToReservation(it) }.singleOrNull()
        }
    }

    fun getAllReservation(): List<ReservationInterface?> {
        return transaction {
            Reservation.selectAll().map { rowToReservation(it) }
        }
    }

    fun updateReservation(id : Int, stateUpdated : Boolean?) {
        transaction {
            Reservation.update({ Reservation.id.eq(id) }) {
                it[state] = stateUpdated
                it[begin] = LocalDateTime.now()
                it[end] = LocalDateTime.now().plusDays(2)
                it[room] = "NA8"
                it[type] = "subject"
            }
        }
    }

    fun deleteReservation() {
        transaction {
            Reservation.deleteWhere { Reservation.id.eq(5) }
        }
    }

    private fun rowToReservation(get: ResultRow?): ReservationInterface? {
        if (get == null) {
            return null
        }

        var id = get[Reservation.id]
        var state = get[Reservation.state]
        var begin = get[Reservation.begin]
        var end = get[Reservation.end]
        var room_name = get[Reservation.room]
        var type = get[Reservation.type]

        return mediatorCreation.createReserva(type, id, state, begin.toString(), end.toString(), mediatorBehaviour.getRoom(room_name)!!)
    }

}