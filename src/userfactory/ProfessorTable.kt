package com.appuah.userfactory

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.booleanLiteral


object ProfessorTable : Table() {


    val username = varchar("username",256).primaryKey()
    val password = varchar("password",256)
    val type = varchar("role",256)
    val name = varchar("name",256)
    val surname = varchar("surname",256)
    val phoneNumber = varchar("phone_number",9)
    val email = varchar("email",256)
    val office = varchar("office",256)
    val is_associatted = bool("is_associated") // no sé si boolean o bool, preguntar a dani
    // añadir is associated
}

