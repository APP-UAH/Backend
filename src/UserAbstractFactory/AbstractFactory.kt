package com.appuah.UserAbstractFactory

import com.appuah.userentities.ExternalUser
import com.appuah.userentities.InternalUser

interface AbstractFactory {


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