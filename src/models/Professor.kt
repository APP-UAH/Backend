package com.appuah.models

import io.ktor.auth.*
import models.User
import java.io.Serializable

// docu: https://devexperto.com/data-classes-kotlin/
data class Professor(
        override val username: String,
        override val password: String,
        override  val role: String,
        val name: String,
        val surname: String,
        val phoneNumber: String,
        val email: String,
        val office:String,
): Serializable, Principal, User(username, password, role)

// serializable es un patrón marker interface

