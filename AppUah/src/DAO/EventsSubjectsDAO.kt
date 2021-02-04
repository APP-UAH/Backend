package com.appuah.DAO

import com.appuah.Tables.EventsSubjects
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class EventsSubjectsDAO {

    fun addEvenSubject(id: Int, code: String, plan: String){
        transaction {
            EventsSubjects.insert {
                it[id_Events] = id
                it[code_Subjects] = code
                it[plan_Subjects] = plan
            }
        }
    }

}