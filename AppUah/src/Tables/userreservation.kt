package com.appuah.Tables

import Tables.Reservation
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object userreservation : Table() {

    val id_Reservation: Column<String> = text("id_Reservation")
    val username_Users: Column<String> = varchar("username_Users", 256)
}