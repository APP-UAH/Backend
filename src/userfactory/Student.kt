package com.appuah.userfactory

import io.ktor.auth.*
import java.io.Serializable

data class Student(
    override val username: String,
    override val password: String,
    override val type: String,
    val name: String,
    val surname: String,
    val email: String,
    val is_deputy: Boolean): Serializable, Principal, User
