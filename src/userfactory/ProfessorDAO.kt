package com.appuah.userfactory

import com.appuah.database.DatabaseSingleton

class ProfessorDAO : UserFactory {

    val dbConection: DatabaseSingleton
        get() {
            TODO()
        }

    override suspend fun addUser(): Professor {
        TODO("Not yet implemented")
    }

    override suspend fun removeUser(): Professor {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): Professor {
        TODO("Not yet implemented")
    }

    override suspend fun logOut(): Professor {
        TODO("Not yet implemented")
    }


}