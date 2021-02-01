package com.appuah.DAO

import com.appuah.Tables.ProfessorSubjects
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
                joinType = JoinType.RIGHT,
                additionalConstraint = { StudentSubjects.username_student.eq(username) }).selectAll()
                .map { rowToSubject(it)!! }
        }
    }

    private fun rowToSubject(get: ResultRow): String? {

        return get[Subjects.name]
    }


}