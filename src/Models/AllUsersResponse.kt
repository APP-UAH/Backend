package com.appuah.Models

import Builder.User

data class AllUsersResponse (val students: List<User?>,
                             val professors: List<User?>){
}