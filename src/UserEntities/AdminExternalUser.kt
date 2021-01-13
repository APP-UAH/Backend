package com.appuah.UserEntities

import com.appuah.userentities.ExternalUser

class AdminExternalUser(
    override val username: String,
    override val password: String,
    override val type: String,
    override val university: String
) : ExternalUser {


}