package com.example.fitnessapp.feature.schedule.state

import com.example.fitnessapp.base.state.LoadingState
import com.example.fitnessapp.feature.schedule.model.LessonModel

data class ScheduleState(
    val loadingState: LoadingState = LoadingState.Loading,
    val lessons: List<LessonModel> = listOf()
)