package com.appuah.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseSingleton() {


    // info (coroutines) de esta función en: https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html
    suspend fun <T> dbQuery(
            block: () -> T): T =
            withContext(Dispatchers.IO) {
                transaction { block() }
            }
}