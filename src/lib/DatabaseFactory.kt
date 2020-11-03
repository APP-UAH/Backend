package com.appuah.lib

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert

object DatabaseFactory {

    fun init(){
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(Users)
            SchemaUtils.create(Todos)
            }
        }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")   //org.postgresql.Driver 
        config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")     //jdbc:postgresql:AppUah?user=postgres&password=postgres&ssl=true;
        config.maximumPoolSize = 20
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        val user = System.getenv("DB_USER")
        if (user != null) {
            config.username = user
        }
        val password = System.getenv("DB_PASSWORD")
        if (password != null) {
            config.password = password
        }
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(
        block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}