package Tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object Reservation : Table(){

    val id: Column<String> = text("id")
    val is_booked : Column<Boolean?> = bool("is_booked").nullable()
    val begin : Column<LocalDateTime> = datetime("begin")
    val end : Column<LocalDateTime> = datetime("end")
    val room: Column<String> = varchar("name", 256)
    val type: Column<String> = varchar("type", 256)
    override val primaryKey = PrimaryKey(id, name = "PK_id")

}