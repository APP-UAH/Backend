package Builder

class User private constructor(
        var username: String?,
        val password: String?,
        val type: Int?,
        val name: String?,
        val surname: String?,
        val email: String?,
        val phone_number: String?,
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
            var phone_number: String? = null,
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

        fun phone_number(phone_number: String) = apply { this.phone_number = phone_number }

        fun email(email: String) = apply { this.email = email }

        fun office(office: String) = apply { this.office = office }

        fun isAssociated(isAssociated: Boolean) = apply { this.isAssociated = isAssociated }

        fun isDeputy(isDeputy: Boolean) = apply { this.isDeputy = isDeputy }

        fun build() = User(username, password, type, name, surname, phone_number, email, office, isAssociated, isDeputy)

        fun getData() = "Builder(username=$this.username, password=$this.password, type=$this.type, name=$this.name, surname=$this.surname, " +
                "email=$this.email, office=$this.office, isAssociated=$this.isAssociated, isDeputy=$this.isDeputy)"

    }

    fun getData(): String? {
        return ("username= " + this.username + ", password= " + this.password + ", type= " + this.type + ", name= " + this.name +
                ", surname= " + this.surname + ", email= " + this.email + ", office= " + this.office + ", isAssociated= " + this.isAssociated +
                ", isDeputy= " + this.isDeputy)
    }

}
