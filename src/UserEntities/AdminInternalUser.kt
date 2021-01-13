package com.appuah.UserEntities

import com.appuah.userentities.InternalUser

class AdminInternalUser(override val username: String,
                        override val password: String,
                        override val type: String) : InternalUser {
}