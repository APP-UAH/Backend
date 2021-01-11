package com.appuah.userfactory

import io.ktor.auth.*
import java.io.Serializable

data class Admin(
    override val username: String,
    override val password: String,
    override val type: String): Serializable, Principal, User
