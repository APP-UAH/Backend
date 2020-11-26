package com.appuah.database

import com.appuah.models.ProfessorUniversidad

interface ProfessorFactory {

    // Esta clase - interfaz - es la que actúa como intemediario entre el backend y nuestra base de datos
    // to-do pasa por aquí 


    val dbConection:DatabaseSingleton

    suspend fun addProfessor(username: String, password: String, name: String, surname: String, phoneNumber: String, email: String, office: String): ProfessorUniversidad?
    suspend fun getProfessor(username: String): ProfessorUniversidad?
    suspend fun getAllProfessor(): ArrayList<ProfessorUniversidad>?
}
