package models

import io.ktor.auth.Principal
import java.io.Serializable

abstract class User(
        open val username: String,
        open val password: String,
        open val role: String
): Serializable, Principal