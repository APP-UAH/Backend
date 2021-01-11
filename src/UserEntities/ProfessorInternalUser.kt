package com.appuah.UserEntities

import com.appuah.userentities.InternalUser
import com.appuah.userfactory.User
import io.ktor.auth.*
import java.io.Serializable

class ProfessorInternalUser(
    override val username: String,
    override val password: String,
    override val type: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String,
    val office: String,
    val is_associatted: Boolean
) : InternalUser {

    override fun toString(): String {
        return "ProfessorInternalUser(username='$username', password='$password', type='$type', name='$name', surname='$surname', phoneNumber='$phoneNumber', email='$email', office='$office', is_associatted=$is_associatted)"
    }
}