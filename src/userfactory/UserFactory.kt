package com.appuah.userfactory

import com.appuah.database.DatabaseSingleton

interface UserFactory {


    suspend fun addUser ():User
    suspend fun removeUser():User
    suspend fun deleteUser(): User
    suspend fun logOut():User


}
