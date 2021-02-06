package com.appuah.Models

import ReservationEntities.ReservationInterface

data class ReservationResponse (
        val reserva : ReservationInterface?,
        val name_subject : String?
){
}