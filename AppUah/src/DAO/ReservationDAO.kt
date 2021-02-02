package DAO

import Adapter.Adapter
import Mediator.BehavioralMediator
import Mediator.CreationMediator
import ReservationEntities.ReservationInterface
import Tables.Reservation
import com.appuah.Tables.userreservation
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

    fun addReservation(reserva : ReservationInterface, condition : String, username: String) {
        transaction {
            Reservation.insert {
                it[is_booked] = adapter.adaptStateToBoolean(reserva.state)
                it[begin] = reserva.begin
                it[end] = reserva.end
                it[room] = reserva.room.name
                it[type] = condition
            }
        }
    }

    fun getReservation(id : String, username: String): ReservationInterface? {
        return transaction {
            Reservation.select() {Reservation.id.eq(id)}.map { rowToReservation(it) }.singleOrNull()
        }
    }

    fun getReservationByUsername(username : String): List<ReservationInterface?> {
        return transaction { Join(Reservation, userreservation, onColumn = Reservation.id, otherColumn = userreservation.id_reservation, joinType = JoinType.INNER,
            additionalConstraint = {userreservation.username_users.eq(username)}).selectAll().map { rowToReservation(it)}}
    }

    fun getAllReservation(): List<ReservationInterface?> {
        return transaction {
            Reservation.selectAll().map { rowToReservation(it) }
        }
    }

    fun updateReservation(newReserva : ReservationInterface, condition: String) {
        transaction {
            Reservation.update({ Reservation.id.eq(newReserva.id) }) {
                it[is_booked] = adapter.adaptStateToBoolean(newReserva.state)
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

        var reserva = mediatorCreation.createReserva(
                get[Reservation.type],
                get[Reservation.id],
                get[Reservation.is_booked],
                get[Reservation.begin],
                get[Reservation.end],
                mediatorBehaviour.getRoom(get[Reservation.room])!!)
        return reserva
    }

}