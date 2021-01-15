package com.appuah.Builder

class UserBuilder private constructor(
    val username: String?,
    val password: String?,
    val type: Int?,
    val name: String?,
    val surname: String?,
    val email: String?,
    val office: String?,
    val isAssociated: Boolean?,
    val isDeputy: Boolean?
) {
    data class Builder(
        var username: String? = null,
        var password: String? = null,
        var type: Int? = null,
        var name: String? = null,
        var surname: String? = null,
        var email: String? = null,
        var office: String? = null,
        var isAssociated: Boolean? = null,
        var isDeputy: Boolean? = null
    ) {

        fun username(username: String) = apply { this.username = username }

        fun password(password: String) = apply { this.password = password }

        fun type(type: Int) = apply { this.type = type }

        fun name(name: String) = apply { this.name = name }

        fun surname(surname: String) = apply { this.surname = surname }

        fun email(username: String) = apply { this.email = email }

        fun office(username: String) = apply { this.office = office }

        fun isAssociated(isAssociated: Boolean) = apply { this.isAssociated = isAssociated }

        fun isDeputy(isDeputy: Boolean) = apply { this.isDeputy = isDeputy }

        fun build() = UserBuilder(username, password, type, name, surname, email, office, isAssociated, isDeputy)
    }
}
