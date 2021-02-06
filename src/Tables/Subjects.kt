package com.appuah.Tables

import Tables.Professor
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Subjects: Table() {

    val code: Column<String> = varchar("code", 256)
    val name: Column<String> = varchar("name", 256)
    val plan: Column<String> = varchar("plan", 256)

}