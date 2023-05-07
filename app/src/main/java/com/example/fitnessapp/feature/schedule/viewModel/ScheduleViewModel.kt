package com.example.fitnessapp.feature.schedule.viewModel

import androidx.lifecycle.ViewModel
import com.example.fitnessapp.feature.schedule.state.ScheduleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

interface ScheduleViewModel {
    val state: StateFlow<ScheduleState>
}

class ScheduleViewModelImpl : KoinComponent, ScheduleViewModel, ViewModel() {
    override val state: StateFlow<ScheduleState>
        get() = _state

    private val _state: MutableStateFlow<ScheduleState> = MutableStateFlow(ScheduleState())

}