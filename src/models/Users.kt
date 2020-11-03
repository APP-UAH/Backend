package models

import io.ktor.auth.Principal
import java.io.Serializable

data class User(
    val userId: Int,
    val email: String,
    val passwordHash: String) : Serializable, Principal