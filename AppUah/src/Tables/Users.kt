package Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users : Table(){
    val username: Column<String> = Users.varchar("username", 256)
    val password: Column<String> = Users.varchar("password", 256)
    val type: Column<Int> = Users.integer("type")
    override val primaryKey = PrimaryKey(Users.username, name = "PK_id")
}