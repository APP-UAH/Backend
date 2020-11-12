package com.appuah.database

import com.appuah.database.DatabaseFactory.dbQuery
import com.appuah.models.ProfessorUniversidad
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement

class TodoDatabaseInterface : DatabaseInterface {


    override suspend fun addProfessor(username: String,password: String,name: String, surname:String, phoneNumber:String,
                                 email:String, office:String): ProfessorUniversidad? {
        var statement: InsertStatement<Number>? = null

        dbQuery {

            statement = Professor.insert{
                it[Professor.username] = username
                it[Professor.password] = password
                it[Professor.name] = name
                it[Professor.surname] = surname
                it[Professor.phoneNumber] = phoneNumber
                it[Professor.email] = email
                it[Professor.office] = office
            }
        }
        return rowToProfessor(statement?.resultedValues?.get(0))
    }

    override suspend fun selectProfessor(username: String): ProfessorUniversidad? {
        var statement: SelectStatement<Number>? = null

        dbQuery {

            statement = Professor.select{Professor.username.eq(username)}
        }
        return rowToProfessor(statement?.resultedValues?.get(0))
    }

    private fun rowToProfessor(get: ResultRow?): ProfessorUniversidad? {
        if (get == null) {
            return null
        }
        return ProfessorUniversidad(
                // checar alt intro
                username = get[Professor.username],
                password = get[Professor.password],
                name = get[Professor.name],
                surname = get[Professor.surname],
                phoneNumber = get[Professor.phoneNumber],
                email = get[Professor.email],
                office = get[Professor.office],
        )
    }


}