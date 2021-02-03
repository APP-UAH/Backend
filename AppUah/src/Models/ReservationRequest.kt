package com.appuah.Models

data class ReservationRequest(
    val id: String,
    val room_name: String,
    val state: Boolean?,
    val begin: String,
    val end: String,
    val username: String,
    val type: String,
    val id_Subject: String,
    val plan_Subject: String) {
}