package com.appuah.Tables

import Tables.Reservation
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object userreservation : Table() {

    val id_reservation: Column<String> = text("id_reservation").references(Reservation.id)
    val username_users: Column<String> = varchar("username_users", 256).references(Users.username)
}