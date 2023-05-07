package com.example.fitnessapp.feature.schedule.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trainer(
    val id: String,
    @SerialName("full_name")
    val fullName: String
)
