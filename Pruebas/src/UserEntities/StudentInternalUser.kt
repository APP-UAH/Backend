package com.appuah.UserEntities

import com.appuah.UserEntities.InternalUser

class StudentInternalUser(
    override val username: String,
    override val password: String,
    override val type: String
) : InternalUser {

    private var name: String = ""

    var surname: String = ""

    var email: String = ""

    var is_deputy: Boolean = false

    override fun toString(): String {
        return "StudentInternalUser(username='$username', password='$password', type='$type', name='$name', surname='$surname', email='$email', is_deputy=$is_deputy)"
    }
}