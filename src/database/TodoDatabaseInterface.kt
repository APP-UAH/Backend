package com.appuah.database

import com.appuah.database.DatabaseFactory.dbQuery
import com.appuah.database.Professors.username
import com.appuah.models.Professor
import models.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement

class TodoDatabaseInterface : DatabaseInterface {


    override suspend fun addProfessor(username: String,password: String,name: String, surname:String, phoneNumber:String,
                                 email:String, office:String): Professor? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = Professors.insert {
                it[Professors.username] = username
                it[Professors.password] = password
                it[Professors.name] = name
                it[Professors.surname] = surname
                it[Professors.phoneNumber] = phoneNumber
                it[Professors.email] = email
                it[Professors.office] = office
            }
        }
        return rowToProfessor(statement?.resultedValues?.get(0))
    }

    private fun rowToProfessor(get: ResultRow?): Professor? {
        if (get == null) {
            return null
        }
        return Professor(
                // checar alt intro
                username = get[Professors.username],
                password = get[Professors.password],
                name = get[Professors.name],
                surname = get[Professors.surname],
                phoneNumber = get[Professors.phoneNumber],
                email = get[Professors.email],
                office = get[Professors.office],
        )
    }


}