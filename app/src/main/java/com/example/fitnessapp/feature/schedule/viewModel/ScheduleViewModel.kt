package com.example.fitnessapp.feature.schedule.viewModel

import com.example.fitnessapp.base.state.LoadingState
import com.example.fitnessapp.base.viewModel.BaseViewModelImpl
import com.example.fitnessapp.feature.schedule.model.LessonDate
import com.example.fitnessapp.feature.schedule.model.ScheduleListItem
import com.example.fitnessapp.feature.schedule.service.ScheduleService
import com.example.fitnessapp.feature.schedule.state.ScheduleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate


class ScheduleViewModel : KoinComponent, BaseViewModelImpl() {

    private val service: ScheduleService by inject()

    val state: StateFlow<ScheduleState>
        get() = _state

    private val _state: MutableStateFlow<ScheduleState> = MutableStateFlow(ScheduleState())
    override fun onViewShown() {
        super.onViewShown()
        jobs.add(scope.launch {
            exceptionHandleable(
                executionBlock = {
                    val data = service.getLessons()
                        .sortedWith(
                            compareBy(
                                { it.lesson.date },
                                { it.lesson.startTime },
                                { it.lesson.endTime })
                        )
                    val result = mutableListOf<ScheduleListItem>()
                    var previousDate: LocalDate? = null
                    data.forEach {
                        if (previousDate == null || !it.lesson.date.isEqual(previousDate)) {
                            result.add(LessonDate(it.lesson.date))
                            previousDate = it.lesson.date
                        }
                        result.add(it)
                    }
                    _state.update {
                        it.copy(
                            loadingState = LoadingState.Success,
                            scheduleItems = result
                        )
                    }
                },
                failureBlock = { error ->
                    _state.update { it.copy(loadingState = LoadingState.Error(error)) }
                })
        })
    }

}