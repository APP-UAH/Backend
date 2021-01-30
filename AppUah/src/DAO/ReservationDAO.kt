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
                it[begin] = reserva.begin
                it[end] = reserva.end
                it[room] = reserva.room.name
                it[type] = condition
            }
        }
    }

    fun getReservation(id : String): ReservationInterface? {
        return transaction {
            Reservation.select { Reservation.id.eq(id) }.map { rowToReservation(it) }.singleOrNull()
        }
    }

    fun getAllReservation(): List<ReservationInterface?> {
        return transaction {
            Reservation.selectAll().map { rowToReservation(it) }
        }
    }

    fun updateReservation(newReserva : ReservationInterface, condition: String) {
        transaction {
            Reservation.update({ Reservation.id.eq(newReserva.id) }) {
                it[state] = adapter.adaptStateToBoolean(newReserva.state)
                it[begin] = newReserva.begin
                it[end] = newReserva.end
                it[room] = newReserva.room.name
                it[type] = condition
            }
        }
    }

    fun deleteReservation(id: String) {
        transaction {
            Reservation.deleteWhere { Reservation.id.eq(id) }
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