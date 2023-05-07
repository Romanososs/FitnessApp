package com.example.fitnessapp.feature.schedule.model

import com.example.fitnessapp.base.utils.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Lesson(
    val name: String,
    val place: String,
    @SerialName("coach_id")
    val coachId: String,
    val startTime: String,
    val endTime: String,
    @Serializable(DateSerializer::class)
    val date: LocalDate,
)