package com.appuah.Models

data class AddSubjectRequest(
    var username: String,
    var plan: String,
    var subjectCodes: String,
    var type:Int
)