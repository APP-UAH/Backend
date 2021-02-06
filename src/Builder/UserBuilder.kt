package Builder

class UserBuilder {

    // el mediator tendrá que implementar un if para elegir qué función escoge

    companion object {

        fun buildProfessor(
            username: String,
            password: String,
            type: Int,
            name: String,
            surname: String,
            phone_number: String,
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
                .phone_number(phone_number)
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