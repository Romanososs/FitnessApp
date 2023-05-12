package com.example.fitnessapp.feature.schedule.model

import java.time.LocalDate

data class LessonDate(
    val date: LocalDate
) : ScheduleListItem() {
    override val type: Int
        get() = DATE_ITEM
}