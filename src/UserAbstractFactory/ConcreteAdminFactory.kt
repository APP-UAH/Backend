package com.appuah.UserAbstractFactory

import com.appuah.UserEntities.AdminExternalUser
import com.appuah.UserEntities.AdminInternalUser
import com.appuah.UserEntities.ProfessorExternalUser
import com.appuah.UserEntities.ProfessorInternalUser
import com.appuah.userentities.ExternalUser
import com.appuah.userentities.InternalUser

class ConcreteAdminFactory:AbstractFactory {

    override fun createInternalUser(
        username: String,
        password: String,
        type: String
    ): InternalUser {


        return AdminInternalUser(username, password, type)
    }


    override fun createExternalUser(
        username: String,
        password: String,
        type: String,
        university:String
    ): ExternalUser {

        return AdminExternalUser(username, password, type, university)
    }


}