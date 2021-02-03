package DAO

import Adapter.Adapter
import Mediator.BehavioralMediator
import Mediator.CreationMediator
import ReservationEntities.ReservationInterface
import RoomEntities.RoomInterface
import Tables.Reservation
import com.appuah.Tables.userreservation
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

import java.time.LocalDateTime
import java.time.ZoneId

class ReservationDAO {

    var mediatorBehaviour: BehavioralMediator
    val mediatorCreation = CreationMediator()
    val adapter = Adapter()
    val zone = ZoneId.of("Europe/Madrid")

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

    fun getReservation(id : String): ReservationInterface? {
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

    fun getAllRoomsFromDB(begin: LocalDateTime, end: LocalDateTime): List<RoomInterface?> {
        val beginDay = begin.toLocalDate().atStartOfDay()
        val endDay = end.toLocalDate().atStartOfDay().plusDays(1).minusSeconds(1);
        return transaction {
            Reservation.select { Reservation.begin.between(beginDay, end) and Reservation.end.between(begin, endDay) }
                    .map { rowToRoom(it) }
        }
    }

    fun getPendingReservationFromDB(): List<ReservationInterface?> {
        return  transaction {
            Reservation.select { Reservation.is_booked.isNull()}.map { rowToReservation(it) }
        }
    }

    fun update(newReserva : ReservationInterface, condition: String) {
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

    private fun rowToRoom(get: ResultRow?): RoomInterface?{
        if (get == null) {
            return null
        }

        return mediatorBehaviour.getRoom(get[Reservation.room])
    }

}