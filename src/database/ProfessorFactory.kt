package com.appuah.database

import com.appuah.models.Professor

interface ProfessorFactory {

    // Esta clase - interfaz - es la que actúa como intemediario entre el backend y nuestra base de datos
    // to-do pasa por aquí 


    val dbConection:DatabaseSingleton

    suspend fun addProfessor(username: String, password: String, name: String, surname: String, phoneNumber: String, email: String, office: String): Professor?
    suspend fun updateProfessor(prevUsername:String,professor:Professor): Professor?
    suspend fun deleteProfessor(username: String): Professor?

    suspend fun getProfessor(username: String): Professor?
    suspend fun getAllProfessor(): List<Professor?>

    suspend fun login(username: String, password: String): Boolean?
    //suspend fun logout()
    //suspend fun getCalendarEvents()
}
