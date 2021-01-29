package DAO

import Builder.User
import Mediator.BehavioralMediator
import Mediator.CreationMediator
import Tables.Professor
import Tables.Student
import Tables.Admin
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAO {
    var mediatorBehaviour: BehavioralMediator
    val mediatorCreation = CreationMediator()

    constructor(mediator: BehavioralMediator) {
        this.mediatorBehaviour = mediator
    }

    fun addProfessor() {
        transaction {
            Professor.insert {
                it[Professor.username] = "SalvadorUsername"
                it[Professor.password] = "Contrasenna de salvador"
                it[Professor.type] = 2
                it[Professor.name] = "Salvador"
                it[Professor.surname] = "Oton Tortosa"
                it[Professor.phone_number] = "918856679"
                it[Professor.email] = "salvador.oton@uah.es"
                it[Professor.office] = "N312"
                it[Professor.is_associated] = false
            }
        }
    }

    fun getProfessor(): User? {
        return transaction {
            Professor.select { Professor.username.eq("SalvadorUsername") }.map { rowToProfessor(it) }.singleOrNull()
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
        var username = get[Professor.username]
        var password = get[Professor.password]
        var type = get[Professor.type]
        var name = get[Professor.name]
        var surname = get[Professor.surname]
        var phone_number = get[Professor.phone_number]
        var email = get[Professor.email]
        var office = get[Professor.office]
        var isAsociated = get[Professor.is_associated]
        return mediatorCreation.buildUser(username, password, type, name, surname, phone_number, email, office, true, isAsociated)
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
    fun addStudent() {
        transaction {
            Student.insert {
                it[Student.username] = "AlvaroUsername"
                it[Student.password] = "Contrasenna de alvaro"
                it[Student.type] = 1
                it[Student.name] = "Alvaro"
                it[Student.surname] = "Golbano Duran"
                it[Student.email] = "alvaro.golbano@edu.uah.es"
                it[Student.is_deputy] = true

            }
        }
    }

    fun getStudent(): User? {
        return transaction {
            Student.select { Student.username.eq("AlvaroUsername") }.map { rowToStudent(it) }.singleOrNull()
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

    fun deleteStudent() {
        transaction {
            Student.deleteWhere { Student.username.eq("AlvaroUsername") }
        }
    }

    private fun rowToStudent(get: ResultRow?): User? {
        if (get == null) {
            return null
        }
        var username = get[Student.username]
        var password = get[Student.password]
        var type = get[Student.type]
        var name = get[Student.name]
        var surname = get[Student.surname]
        var email = get[Student.email]
        var isDeputy = get[Student.is_deputy]
        return mediatorCreation.buildUser(username, password, type, name, surname, "phone_number", email, "office", isDeputy, true)
    }

    //---------------------------------------------------------------------------------------------------------------------------------------
    fun addAdmin() {
        transaction {
            Admin.insert {
                it[Admin.username] = "Admin"
                it[Admin.password] = "root"
                it[Admin.type] = 3
            }
        }
    }

    fun getAdmin(): User? {
        return transaction {
            Admin.select { Admin.username.eq("Admin") }.map { rowToAdmin(it) }.singleOrNull()
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
                it[Admin.username] = "Nimda"
                it[Admin.password] = "toor"
            }
        }
    }

    fun deleteAdmin() {
        transaction {
            Admin.deleteWhere { Admin.username.eq("Admin") }
        }
    }

    private fun rowToAdmin(get: ResultRow?): User? {
        if (get == null) {
            return null
        }
        var username = get[Admin.username]
        var password = get[Admin.password]
        var type = get[Admin.type]
        return mediatorCreation.buildUser(username, password, type, "name", "surname", "phone_number", "email", "office", true, true)
    }

}
