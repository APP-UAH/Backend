package Singleton

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import java.io.PrintWriter
import java.util.*


object DatabaseSingleton {

    fun init() {
        Database.connect(hikari())
    }

    private fun hikari(): HikariDataSource {

        val props = Properties()
        val username = System.getenv("DB_USERNAME")
        val password = System.getenv("DB_PASSWORD")
        val database = System.getenv("DATABASE")
        val host = System.getenv("DB_HOST")
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource")
        props.setProperty("dataSource.user", username)
        props.setProperty("dataSource.password", password)
        props.setProperty("dataSource.databaseName", database)
        props.setProperty("dataSource.serverName", host)
        props["dataSource.logWriter"] = PrintWriter(System.out)

        val config = HikariConfig(props)
        config.validate()
        return HikariDataSource(config)

    }

}

