package com.appuah.UserAbstractFactory

import com.appuah.UserEntities.StudentInternalUser
import com.appuah.UserEntities.StudentExternalUser
import com.appuah.userentities.ExternalUser
import com.appuah.userentities.InternalUser

class ConcreteStudentFactory : AbstractFactory {


    override fun createInternalUser(
        username: String,
        password: String,
        type: String
    ): InternalUser {


        return StudentInternalUser(username, password, type)
    }


    override fun createExternalUser(
        username: String,
        password: String,
        type: String,
        university:String
    ): ExternalUser {

        return StudentExternalUser(username, password, type, university)
    }


}