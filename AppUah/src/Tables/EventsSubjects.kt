package com.appuah.Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object EventsSubjects : Table() {

    val id_Events: Column<String> = text("id_events")
    val code_Subjects: Column<String> = text("code_subjects")
    val plan_Subjects: Column<String> = text("plan_subjects")

}