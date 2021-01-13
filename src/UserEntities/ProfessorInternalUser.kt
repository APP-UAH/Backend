package com.appuah.UserEntities

import com.appuah.userentities.InternalUser
import com.appuah.userfactory.User
import io.ktor.auth.*
import java.io.Serializable

class ProfessorInternalUser(
    override val username: String,
    override val password: String,
    override val type: String,
) : InternalUser {



    val name: String
        get() {
            return this.name
        }
    val surname: String
        get() {
            return this.surname
        }
    val phoneNumber: String
        get() {
            return this.phoneNumber
        }
    val email: String
        get() {
            return this.email
        }
    val office: String
        get() {
            return this.office
        }
    val is_associatted: Boolean
        get() {
            return this.is_associatted
        }

    override fun toString(): String {
        return "ProfessorInternalUser(username='$username', password='$password', type='$type', name='$name', surname='$surname', phoneNumber='$phoneNumber', email='$email', office='$office', is_associatted=$is_associatted)"
    }
}