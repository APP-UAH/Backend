package com.appuah.Tables

import Tables.Professor
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ProfessorSubjects: Table() {

    val code_subjects: Column<String> = varchar("code_subjects", 256)
    val plan_subjects: Column<String> = varchar("plan_subjects", 256)
    val username_professor: Column<String> = varchar("username_professor", 256)

}