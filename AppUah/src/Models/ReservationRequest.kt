package com.appuah.Models

import java.time.LocalDateTime

data class ReservationRequest(
    val id: String,
    val room_name: String,
    val state: Boolean?,
    val begin: LocalDateTime,
    val end: LocalDateTime,
    val username: String,
    val type: String,
    val id_Subject: String,
    val plan_Subject: String) {
}