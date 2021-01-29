package Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Admin : Table() {
    val username: Column<String> = Admin.varchar("username", 256)
    val password: Column<String> = Admin.varchar("password", 256)
    val type: Column<Int> = Admin.integer("type")

}