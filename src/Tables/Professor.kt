package Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object Professor : Table() {
    val username: Column<String> = varchar("username", 256)
    val password: Column<String> = varchar("password", 256)
    val type: Column<Int> = integer("type")
    val name: Column<String> = varchar("name", 256)
    val surname: Column<String> = varchar("surname", 256)
    val phone_number: Column<String> = varchar("phone_number", 9)
    val email: Column<String> = varchar("email", 256)
    val office: Column<String> = varchar("office", 256)
    val is_associated: Column<Boolean> = bool("is_associated")
    override val primaryKey = PrimaryKey(Professor.username, name = "PK_id")
}