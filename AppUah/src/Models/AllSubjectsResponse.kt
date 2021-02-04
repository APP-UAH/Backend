package com.appuah.Models

import Models.SubjectsResponse

data class AllSubjectsResponse (val plans:ArrayList<String>,
                                val subjects:ArrayList<SubjectsResponse>){
}