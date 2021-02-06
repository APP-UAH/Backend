package com.appuah.DAO

import ReservationEntities.ReservationInterface
import Tables.Reservation
import com.appuah.Tables.userreservation
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserReservationDAO {

    fun addUserReservation(id: String, username: String){
        transaction {
            userreservation.insert {
                it[id_reservation] = id
                it[username_users] = username
            }
        }
    }

    fun getReservationId(username: String): List<String?>{
        return transaction {
            userreservation.select { userreservation.username_users.eq(username) }.map { rowToUserReservation(it) }
        }
    }

    fun rowToUserReservation(get: ResultRow?): String? {
        if (get == null) {
            return null
        }

        var id = get[userreservation.id_reservation]


        return id
    }
}