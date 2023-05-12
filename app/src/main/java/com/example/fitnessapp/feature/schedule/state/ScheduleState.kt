package com.example.fitnessapp.feature.schedule.state

import com.example.fitnessapp.base.state.LoadingState
import com.example.fitnessapp.feature.schedule.model.LessonModel
import com.example.fitnessapp.feature.schedule.model.ScheduleListItem

data class ScheduleState(
    val loadingState: LoadingState = LoadingState.Loading,
    val scheduleItems: List<ScheduleListItem> = listOf()
)