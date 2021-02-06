package com.appuah.DAO

import Tables.Professor
import Tables.Student
import com.appuah.Tables.ProfessorSubjects
import com.appuah.Tables.ProfessorSubjects.code_subjects
import com.appuah.Tables.ProfessorSubjects.plan_subjects
import com.appuah.Tables.ProfessorSubjects.username_professor
import com.appuah.Tables.StudentSubjects
import com.appuah.Tables.Subjects
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class SubjectDAO {

    /*
        return Join(Reservation, userreservation, onColumn = Reservation.id, otherColumn = userreservation.id_Reservation, joinType = JoinType.LEFT,
        additionalConstraint = {userreservation.username_Users.eq(username)}).selectAll().map { rowToReservation(it)}
    */
    fun getProfessorSubjects(username: String): List<String> {

        return transaction {
            Join(ProfessorSubjects,
                Subjects,
                onColumn = ProfessorSubjects.code_subjects,
                otherColumn = Subjects.code,
                joinType = JoinType.INNER,
                additionalConstraint = {
                    ProfessorSubjects.username_professor.eq(username) and ProfessorSubjects.plan_subjects.eq(
                        Subjects.plan
                    )
                }).selectAll()
                .map { rowToSubject(it)!! }
        }

    }

    fun getStudentSubjects(username: String): List<String> {

        return transaction {
            Join(StudentSubjects,
                Subjects,
                onColumn = StudentSubjects.code_subjects,
                otherColumn = Subjects.code,
                joinType = JoinType.INNER,
                additionalConstraint = {
                    StudentSubjects.username_student.eq(username) and StudentSubjects.plan_subjects.eq(
                        Subjects.plan
                    )
                }).selectAll()
                .map { rowToSubject(it)!! }
        }
    }

    fun getAllPlans():List<String>{
        return transaction {
            Subjects.selectAll().map { rowToSubjectPlans(it)!! }
        }
    }

    fun getAllSubjects(): List<String> {
        return transaction {
            Subjects.selectAll().map { rowToSubject(it)!! }
        }
    }

    fun addStudentSubjects(username: String, plan: String, code: String) {
        transaction {
            StudentSubjects.insert{
                it[username_student] = username
                it[code_subjects] = code
                it[plan_subjects] = plan
            }
        }
    }

    fun addProfessorSubjects(username: String, plan: String, code: String) {
        transaction {
            ProfessorSubjects.insert{
                it[username_professor] = username
                it[code_subjects] = code
                it[plan_subjects] = plan
            }
        }
    }

    fun deleteStudentSubject(username: String){
        transaction {
            StudentSubjects.deleteWhere { StudentSubjects.username_student.eq(username) }
        }
    }

    fun deleteProfessorSubject(username: String){
        transaction {
            ProfessorSubjects.deleteWhere { ProfessorSubjects.username_professor.eq(username) }
        }
    }

    private fun rowToSubject(get: ResultRow): String? {

        return get[Subjects.name] + "," + get[Subjects.plan] + "," + get[Subjects.code]
    }

    private fun rowToSubjectPlans(get: ResultRow): String? {

        return get[Subjects.plan]
    }
}