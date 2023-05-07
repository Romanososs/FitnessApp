package com.example.fitnessapp.feature.schedule.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    val trainers: List<Trainer>,
    val lessons: List<Lesson>
)
