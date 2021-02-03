package DAO

import Mediator.BehavioralMediator
import Mediator.CreationMediator
import Tables.Professor
import Tables.Student
import Tables.Admin
import Tables.Users
import Builder.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAO(mediator: BehavioralMediator) {
    var mediatorBehaviour: BehavioralMediator = mediator
    val mediatorCreation = CreationMediator()

    fun getUser(username: String): User? {
        return transaction {
            Users.select { Users.username.eq(username) }.map { rowToUser(it) }.singleOrNull()
        }
    }

    fun getAllUser():List<User?>{
        return transaction {
            Users.selectAll().map { rowToUser(it) }
        }
    }

    private fun rowToUser(get: ResultRow?): User? {
        if (get == null) {
            return null
        }
        val username = get[Users.username]
        val password = get[Users.password]
        val type = get[Users.type]
        return mediatorCreation.buildUser(
            username,
            password,
            type,
            "name",
            "surname",
            "phone_number",
            "email",
            "office",
            true,
            true
        )
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
    fun addProfessor(username:String,password:String,type:Int,name:String,surname:String,phone_number:String,email:String,office:String,is_associated:Boolean) {
        transaction {
            Professor.insert {
                it[Professor.username] = username
                it[Professor.password] = password
                it[Professor.type] = type
                it[Professor.name] = name
                it[Professor.surname] = surname
                it[Professor.phone_number] = phone_number
                it[Professor.email] = email
                it[Professor.office] = office
                it[Professor.is_associated] = is_associated
            }
        }
    }

    fun getProfessor(username:String): User? {
        return transaction {
            Professor.select { Professor.username.eq(username) }.map { rowToProfessor(it) }.singleOrNull()
        }
    }

    fun getAllProfessors(): List<User?> {
        return transaction {
            Professor.selectAll().map { rowToProfessor(it) }
        }
    }

    fun updateProfessor() {
        transaction {
            Professor.update({ Professor.username.eq("SalvadorUsername") }) {
                it[name] = "Rodavlas"
                it[office] = "N213"
                it[is_associated] = true
            }
        }
    }

    fun deleteProfessor() {
        transaction {
            Professor.deleteWhere { Professor.username.eq("SalvadorUsername") }
        }
    }

    private fun rowToProfessor(get: ResultRow?): User? {
        if (get == null) {
            return null
        }
        val username = get[Professor.username]
        val password = get[Professor.password]
        val type = get[Professor.type]
        val name = get[Professor.name]
        val surname = get[Professor.surname]
        val phone_number = get[Professor.phone_number]
        val email = get[Professor.email]
        val office = get[Professor.office]
        val isAsociated = get[Professor.is_associated]
        return mediatorCreation.buildUser(
            username,
            password,
            type,
            name,
            surname,
            phone_number,
            email,
            office,
            true,
            isAsociated
        )
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
    fun addStudent(username:String,password:String,type:Int,name:String,surname:String,email:String,is_deputy:Boolean) {
        transaction {
            Student.insert {
                it[Student.username] = username
                it[Student.password] = password
                it[Student.type] = type
                it[Student.name] = name
                it[Student.surname] = surname
                it[Student.email] = email
                it[Student.is_deputy] = is_deputy
            }
        }
    }

    fun getStudent(username:String): User? {
        return transaction {
            Student.select { Student.username.eq(username) }.map { rowToStudent(it) }.singleOrNull()
        }
    }

    fun getAllStudents(): List<User?> {
        return transaction {
            Student.selectAll().map { rowToStudent(it) }
        }
    }

    fun updateStudent() {
        transaction {
            Student.update({ Student.username.eq("AlvaroUsername") }) {
                it[name] = "Oravla"
                it[is_deputy] = false
                it[surname] = "Duran Golbano"
            }
        }
    }

    fun deleteStudent(username:String) {
        transaction {
            Student.deleteWhere { Student.username.eq(username) }
        }
    }

    private fun rowToStudent(get: ResultRow?): User? {
        if (get == null) {
            return null
        }
        val username = get[Student.username]
        val password = get[Student.password]
        val type = get[Student.type]
        val name = get[Student.name]
        val surname = get[Student.surname]
        val email = get[Student.email]
        val isDeputy = get[Student.is_deputy]
        return mediatorCreation.buildUser(
            username,
            password,
            type,
            name,
            surname,
            "phone_number",
            email,
            "office",
            isDeputy,
            true
        )
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
    fun addAdmin(username:String,password:String,type:Int) {
        transaction {
            Admin.insert {
                it[Admin.username] = username
                it[Admin.password] = password
                it[Admin.type] = type
            }
        }
    }

    fun getAdmin(username:String): User? {
        return transaction {
            Admin.select { Admin.username.eq(username) }.map { rowToAdmin(it) }.singleOrNull()
        }
    }

    fun getAllAdmins(): List<User?> {
        return transaction {
            Student.selectAll().map { rowToStudent(it) }
        }
    }

    fun updateAdmins() {
        transaction {
            Admin.update({ Admin.username.eq("Admin") }) {
                it[username] = "Nimda"
                it[password] = "toor"
            }
        }
    }

    fun deleteAdmin(username: String) {
        transaction {
            Admin.deleteWhere { Admin.username.eq(username) }
        }
    }

    private fun rowToAdmin(get: ResultRow?): User? {
        if (get == null) {
            return null
        }
        val username = get[Admin.username]
        val password = get[Admin.password]
        val type = get[Admin.type]
        return mediatorCreation.buildUser(
            username,
            password,
            type,
            "name",
            "surname",
            "phone_number",
            "email",
            "office",
            true,
            true
        )
    }

}
