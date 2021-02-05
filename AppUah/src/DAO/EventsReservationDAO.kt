package com.appuah.DAO

import ReservationEntities.ReservationInterface
import Tables.Reservation
import com.appuah.Tables.events
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class EventsReservationDAO {

    fun addEventReservation(id: String, id_res: String) {
        transaction {
            events.insert {
                it[events.id] = id
                it[id_Reservation] = id_res
            }
        }
    }

    fun getEvent(id_res:String): String?{
        return transaction {
            events.select { events.id_Reservation.eq(id_res) }.map { rowToEvents(it) }
        }.get(0)
    }

    private fun rowToEvents(get: ResultRow?): String? {
        if (get == null) {
            return null
        }

        var id = get[events.id]

        return id
    }

}