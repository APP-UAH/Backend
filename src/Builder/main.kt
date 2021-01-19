package com.appuah.Builder


fun main(args: Array<String>) {
    println("Hello World!")

    /*
    Main para simular el funcionamiento del patrón builder y poder comprobar su funcionamiento
    tres construcciones diferentes para tres usuarios diferentes
     */
    val student = UserBuilder.buildStudent("carlos.palou",
        "123",2,"carlos","palou","student@edu.uah.com",true)

    var professor = UserBuilder.buildProfessor("antonio.blanco",
    "123",1,"antonio","blanco","teacher@edu.com",
    "NL123",false)


    var admin = UserBuilder.buildAdmin("admin",
        "admin",3)

    //println(professor.toString())

}