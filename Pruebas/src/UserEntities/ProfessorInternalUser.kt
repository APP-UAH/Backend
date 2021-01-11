package com.appuah.UserEntities

import com.appuah.UserEntities.InternalUser

class ProfessorInternalUser(
    override val username: String,
    override val password: String,
    override val type: String
) : InternalUser {

    val name: String
        get() {
            TODO()
        }
    val surname: String
        get() {
            TODO()
        }
    val phoneNumber: String
        get() {
            TODO()
        }
    val email: String
        get() {
            TODO()
        }
    val office: String
        get() {
            TODO()
        }
    val is_associatted: Boolean
        get() {
            TODO()
        }

    override fun toString(): String {
        return "ProfessorInternalUser(username='$username', password='$password', type='$type', name='$name', surname='$surname', phoneNumber='$phoneNumber', email='$email', office='$office', is_associatted=$is_associatted)"
    }
}