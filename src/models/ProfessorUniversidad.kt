package com.appuah.models

import io.ktor.auth.*
import java.io.Serializable

// docu: https://devexperto.com/data-classes-kotlin/
data class ProfessorUniversidad(
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String,
    val office:String
): Serializable, Principal, Person(username, password, name, surname, phoneNumber, email)

// serializable es un patrón marker interface

