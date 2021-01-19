package com.appuah.Builder

import com.appuah.Builder.User.Builder


class UserBuilder {

    /*
    Companion lo utilizamos para englobar todos los métodos que serán invocador por otras clases
    así como sus propiedades estáticas
     */

    companion object {

        fun buildProfessor(
            username: String,
            password: String,
            type: Int,
            name: String,
            surname: String,
            email: String,
            office: String,
            isAssociated: Boolean
        ): User {
            /*
            Realizamos la construcción paso a paso, según lo que nos interese
            como se puede observar, hemos abstraído de la creación a las conexiones del frontend
            ellos solo necesitan darnos los datos y el rol, y un mediador superior se encargará de analizarlo
            y decidir qué construye, sin preocuparse del cómo; teniendo un código desacoplado
             */
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


        }

        fun buildStudent(
            username: String,
            password: String,
            type: Int,
            name: String,
            surname: String,
            email: String,
            isDeputy: Boolean
        ): User {
            // devolveremos un objeto usuario
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

        fun buildAdmin(
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