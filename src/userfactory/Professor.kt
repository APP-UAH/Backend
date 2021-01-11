package com.appuah.userfactory

import io.ktor.auth.*
import java.io.Serializable

// docu: https://devexperto.com/data-classes-kotlin/

data class Professor(
    override val username: String,
    override val password: String,
    override val type: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String,
    val office: String,
    val is_associatted: Boolean // no sé si boolean o bool, preguntar a dani
) : Serializable, Principal, User



