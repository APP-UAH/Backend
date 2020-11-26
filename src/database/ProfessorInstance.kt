package com.appuah.database


import com.appuah.models.ProfessorUniversidad
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertSelectStatement
import org.jetbrains.exposed.sql.statements.InsertStatement

class ProfessorInstance(override val dbConection: DatabaseSingleton) : ProfessorFactory {


    override suspend fun addProfessor(username: String,password: String,name: String, surname:String, phoneNumber:String,
                                 email:String, office:String): ProfessorUniversidad? {

        var statement: InsertStatement<Number>? = null

        this.dbConection.dbQuery {
            statement = ProfessorTable.insert{
                it[ProfessorTable.username] = username
                it[ProfessorTable.password] = password
                it[ProfessorTable.name] = name
                it[ProfessorTable.surname] = surname
                it[ProfessorTable.phoneNumber] = phoneNumber
                it[ProfessorTable.email] = email
                it[ProfessorTable.office] = office
            }
        }
        return rowToProfessor(statement?.resultedValues?.get(0))
    }

    override suspend fun getProfessor(username: String): ProfessorUniversidad? {

        var answer = this.dbConection.dbQuery {
            ProfessorTable.select{ ProfessorTable.username.eq(username)}
        }

        return answer.map { rowToProfessor(it) }.singleOrNull()
    }

    override suspend fun getAllProfessor(): ArrayList<ProfessorUniversidad>? {
        TODO("Not yet implemented")
    }

    private fun rowToProfessor(get: ResultRow?): ProfessorUniversidad? {
        if (get == null) {
            return null
        }
        return ProfessorUniversidad(
                // checar alt intro
                username = get[ProfessorTable.username],
                password = get[ProfessorTable.password],
                name = get[ProfessorTable.name],
                surname = get[ProfessorTable.surname],
                phoneNumber = get[ProfessorTable.phoneNumber],
                email = get[ProfessorTable.email],
                office = get[ProfessorTable.office],
        )
    }


}