package Models

import Builder.User

data class UserResponse(
    val students:List<User?>,
    val professors:List<User?>) {
}