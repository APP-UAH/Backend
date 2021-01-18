package com.appuah.Builder

import com.appuah.Builder.User.Builder


class UserBuilder {

    // el mediator tendrá que implementar un if para elegir qué función escoge

    companion object {

        fun constructProfessor(
            username: String,
            password: String,
            type: Int,
            name: String,
            surname: String,
            email: String,
            office: String,
            isAssociated: Boolean
        ): User {
            val professor = User.Builder()
                .username(username)
                .password(password)
                .type(type)
                .name(name)
                .surname(surname)
                .email(email)
                .office(office)
                .isAssociated(isAssociated)
                .build()


            return professor

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
        ): User {
            val student = User.Builder()
                .username(username)
                .password(password)
                .type(type)
                .name(name)
                .surname(surname)
                .email(email)
                .isDeputy(isDeputy)
                .build()
            return student
        }

        fun constructAdmin(
            username: String,
            password: String,
            type: Int
        ): User {

            val admin = User.Builder()
                .username(username)
                .password(password)
                .type(type)
                .build()
            return admin
        }

    }
}