package com.appuah.database


import com.appuah.userfactory.Professor
import com.appuah.userfactory.ProfessorTable
import com.appuah.userfactory.UserFactory
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserInstance(override val dbConection: DatabaseSingleton) : UserFactory {


    override suspend fun addProfessor(username: String,password: String,name: String, surname:String, phoneNumber:String,
                                 email:String, office:String): Professor? {

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

    override suspend fun updateProfessor(prevUsername:String, professor:Professor): Professor? {


        var answer=ProfessorTable.update({ProfessorTable.username.eq(prevUsername)}){
            it[ProfessorTable.username] = professor.username
            it[ProfessorTable.password] = professor.password
            it[ProfessorTable.name] = professor.name
            it[ProfessorTable.surname] = professor.surname
            it[ProfessorTable.phoneNumber] = professor.phoneNumber
            it[ProfessorTable.email] = professor.email
            it[ProfessorTable.office] = professor.office
        }

        return getProfessor(professor.username)

    }

    override suspend fun deleteProfessor(username: String): Professor? {
        TODO("Not yet implemented")
    }

    override suspend fun getProfessor(username: String): Professor? {

        var answer = this.dbConection.dbQuery {
            ProfessorTable.select{ ProfessorTable.username.eq(username)}
                    .map { rowToProfessor(it) }.singleOrNull()
        }

        return answer
    }

    override suspend fun getAllProfessor(): List<Professor?> {

        var answer = this.dbConection.dbQuery {
            ProfessorTable.selectAll().map { rowToProfessor(it) }
        }

        return answer
    }

    override suspend fun login(username: String, password: String): Boolean? {

        return try{
            val answer = this.dbConection.dbQuery {
                ProfessorTable.select{ ProfessorTable.username.eq(username) and ProfessorTable.password.eq(password)}
                        .map { rowToProfessor(it) }.singleOrNull()
            }
            println(answer)
            true;

        } catch (e: Exception){
            println(e)
            false;
        }

    }

    private fun rowToProfessor(get: ResultRow?): Professor? {
        if (get == null) {
            return null
        }
        return Professor(
                // checar alt intro
                username = get[ProfessorTable.username],
                password = get[ProfessorTable.password],
                type = get[ProfessorTable.type],
                name = get[ProfessorTable.name],
                surname = get[ProfessorTable.surname],
                phoneNumber = get[ProfessorTable.phoneNumber],
                email = get[ProfessorTable.email],
                office = get[ProfessorTable.office],
        )
    }


}