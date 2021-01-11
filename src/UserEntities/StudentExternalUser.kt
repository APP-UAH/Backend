package com.appuah.UserEntities

import com.appuah.userentities.ExternalUser

class StudentExternalUser(
    override val username: String,
    override val password: String,
    override val type: String,
    override val university: String
) : ExternalUser {




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


    override fun toString(): String {
        return "ExternalStudentUser(username='$username', password='$password', type='$type', university='$university', name='$name', surname='$surname', email='$email')"
    }
}