package com.appuah.userfactory

import io.ktor.auth.Principal
import java.io.Serializable

interface User {
    val username: String
    val password: String
    val type: String
}