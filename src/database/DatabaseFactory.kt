package com.appuah.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database


object DatabaseFactory {

    val databaseSingleton = DatabaseSingleton()

    fun init() {
        Database.connect(hikari())

    }

    private fun hikari(): HikariDataSource {
        /* esta función nos permite conectarnos a NUESTRA base de datos

        JDBC_DRIVER -> org.postgresql.Driver   ;;; Utilizamos Postgresql para la base de datos
        JDBC_DATABASE_URL -> jdbc:postgresql:AppUah?user=postgres&password=carlos21&ssl=true    ;;; URL de acceso
        SECRET_KEY -> 629985170873629865   ;;; claves secretas de encriptación
        JWT_SECRET -> 629985170873629865   ;;; claves secretas de encriptación

         */

        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
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

    fun createProfessorInstance ():UserInstance{
        return UserInstance(this.databaseSingleton)
    }


    //fun createDatabaseInstance()
    /*
    * dbQuery tiene que ser un objeto de DatabaseFactory
    * createDatabaseInstance(dbQuery)
    *
    * */




}