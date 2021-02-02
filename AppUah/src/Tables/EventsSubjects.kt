package com.appuah.Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object EventsSubjects : Table() {

    val id_Events: Column<Int> = integer("id_Events")
    val id_Subjects: Column<String> = text("id_Subjects")
    val plan_Subjects: Column<String> = text("Plan_Subjects")

}