package com.appuah.Tables

import Tables.Professor
import Tables.Student
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object StudentSubjects: Table() {


    // creo que hay que crear modelos
    val code_subjects: Column<String> = varchar("code_subjects", 256)
    val plan_subjects: Column<String> = varchar("plan_subjects", 256)
    val username_student: Column<String> = varchar("username_student", 256)

}