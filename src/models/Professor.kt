package com.appuah.models

import io.ktor.auth.*
import java.io.Serializable

// docu: https://devexperto.com/data-classes-kotlin/
data class Professor : Users(
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String,
    val office:String
): Serializable, Principal

//ruta de ver listas de asistencia
//json username salvador oton
// select from Listas where username = salvadorOton

ver lista de asistencia

    this.username

// serializable es un patrón marker interface

