package com.appuah.UserAbstractFactory

import com.appuah.UserEntities.ProfessorExternalUser
import com.appuah.UserEntities.ProfessorInternalUser
import com.appuah.UserEntities.ExternalUser
import com.appuah.UserEntities.InternalUser

class ConcreteProfessorFactory : AbstractFactory{


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