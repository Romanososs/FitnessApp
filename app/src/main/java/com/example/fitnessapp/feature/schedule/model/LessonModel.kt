package com.example.fitnessapp.feature.schedule.model

data class LessonModel(
    val lesson: Lesson,
    val coach: Trainer?
): ScheduleListItem() {
    override val type: Int
        get() = LESSON_CARD_ITEM
}
