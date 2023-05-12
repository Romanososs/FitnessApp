package com.example.fitnessapp.feature.schedule.model


abstract class ScheduleListItem {
    abstract val type: Int

    companion object {
        const val DATE_ITEM = 0
        const val LESSON_CARD_ITEM = 1
    }
}