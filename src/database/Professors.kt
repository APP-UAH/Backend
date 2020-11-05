package com.appuah.database

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object Professors : Table() {
    val username = varchar("username",256).primaryKey()
    val password = varchar("password",256)
    val name = varchar("name",256)
    val surname = varchar("surname",256)
    val phoneNumber = varchar("phone_number",9)
    val email = varchar("email",256)
    val office = varchar("office",256)


}

