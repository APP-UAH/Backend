package com.appuah.UserEntities

import com.appuah.userentities.InternalUser

class StudentInternalUser(
    override val username: String,
    override val password: String,
    override val type: String
) : InternalUser {


    val name: String
        get() {
            return this.name
        }
    val surname: String
        get() {
            return this.surname
        }
    val email: String
        get() {
            return this.email
        }
    val is_deputy: Boolean
        get() {
            return this.is_deputy
        }



    override fun toString(): String {
        return "StudentInternalUser(username='$username', password='$password', type='$type', name='$name', surname='$surname', email='$email', is_deputy=$is_deputy)"
    }
}