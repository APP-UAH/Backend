package com.appuah.Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users: Table(){

    val username: Column<String> = varchar("username", 256)
    val password: Column<String> = varchar("password", 256)
    val type: Column<Int> = integer("type")

}