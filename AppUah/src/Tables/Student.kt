package Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object  Student:Table() {
    val username: Column<String> = Student.varchar("username", 256)
    val password: Column<String> = Student.varchar("password", 256)
    val type: Column<Int> = Student.integer("type")
    val name: Column<String> = Student.varchar("name", 256)
    val surname: Column<String> = Student.varchar("surname", 256)
    val email: Column<String> = Student.varchar("email", 256)
    val is_deputy: Column<Boolean> = Student.bool("is_deputy")
    override val primaryKey = PrimaryKey(Student.username, name = "PK_id")
}