package com.appuah.UserAbstractFactory

import com.appuah.UserEntities.ProfessorExternalUser
import com.appuah.UserEntities.ProfessorInternalUser
import com.appuah.UserEntities.StudentExternalUser
import com.appuah.UserEntities.StudentInternalUser
import com.appuah.userentities.ExternalUser
import com.appuah.userentities.InternalUser

class ConcreteProfessorFactory:AbstractFactory {

    override fun createInternalUser(
        username: String,
        password: String,
        type: String
    ): InternalUser {


        return ProfessorInternalUser(username, password, type)
    }


    override fun createExternalUser(
        username: String,
        password: String,
        type: String,
        university:String
    ): ExternalUser {

        return ProfessorExternalUser(username, password, type, university)
    }

}