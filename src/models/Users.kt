package models

import io.ktor.auth.Principal
import java.io.Serializable

data abstract class User(
        val username: String,
        val password: String,
        val name: String,
        val surname: String,
        val phoneNumber: String,
        val email: String,
): Serializable, Principal