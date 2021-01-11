package com.appuah.UserAbstractFactory

import com.appuah.UserEntities.ExternalUser
import com.appuah.UserEntities.InternalUser


interface AbstractFactory {

    companion object {
        fun getFactory(condition: String?): AbstractFactory? {
            return when (condition) {
                "Student" -> ConcreteStudentFactory()
                "Professor" -> ConcreteProfessorFactory()
                else -> throw Exception("Invalid document type")
            }
        }
    }
    fun createInternalUser(
        username: String,
        password: String,
        type: String
    ): InternalUser

    fun createExternalUser(
        username: String,
        password: String,
        type: String,
        university: String
    ): ExternalUser


}