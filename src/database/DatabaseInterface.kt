package com.appuah.database

import com.appuah.database.Professors.primaryKey
import com.appuah.models.Professor

interface DatabaseInterface {

    // Esta clase - interfaz - es la que actúa como intemediario entre el backend y nuestra base de datos
    // to-do pasa por aquí 



    suspend fun addProfessor(username: String, password: String, name: String, surname: String, phoneNumber: String, email: String, office: String): Professor?
}
