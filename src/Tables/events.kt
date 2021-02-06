package com.appuah.Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object events : Table() {

    val id: Column<String> = text("id")
    val id_Reservation: Column<String> = text("id_reservation")

}