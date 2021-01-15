package com.appuah.Builder

import com.appuah.Builder.UserBuilder.Builder


class Director {

    // el mediator tendrá que implementar un if para elegir qué función escoge

    fun constructProfessor(
        username: String,
        password: String,
        type: Int,
        name: String,
        surname: String,
        email: String,
        office: String,
        isAssociated: Boolean
    ) {
        val professor = Builder()
            .username(username)
            .password(password)
            .type(type)
            .name(name)
            .surname(surname)
            .email(email)
            .office(office)
            .isAssociated(isAssociated)
            .build()

        // en teoría este sería el objeto que le entregaríamos al DAO para hacer el insert (?)
        // professorDAO.insert(preofessor)
    }

    fun constructStudent(
        username: String,
        password: String,
        type: Int,
        name: String,
        surname: String,
        email: String,
        isDeputy: Boolean
    ) {
        val student = Builder()
            .username(username)
            .password(password)
            .type(type)
            .name(name)
            .surname(surname)
            .email(email)
            .isDeputy(isDeputy)
            .build()

    }

    fun constructAdmin(
        username: String,
        password: String,
        type: Int
    ) {

        val admin = Builder()
            .username(username)
            .password(password)
            .type(type)
            .build()

    }


}