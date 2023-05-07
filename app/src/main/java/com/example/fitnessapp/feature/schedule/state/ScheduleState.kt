package com.example.fitnessapp.feature.schedule.state

import com.example.fitnessapp.base.state.LoadingState

data class ScheduleState(
    val loadingState: LoadingState = LoadingState.Loading

)