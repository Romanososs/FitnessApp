package com.example.fitnessapp.feature.schedule.service

import com.example.fitnessapp.feature.schedule.model.LessonModel
import com.example.fitnessapp.feature.schedule.repository.ScheduleRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ScheduleService {
    suspend fun getLessons(): List<LessonModel>
}

class ScheduleServiceImpl : KoinComponent, ScheduleService {
    private val repo: ScheduleRepository by inject()

    override suspend fun getLessons(): List<LessonModel> {
        val response = repo.getSchedule()
        return response.lessons.map { lesson ->
            val coach = response.trainers.find { it.id == lesson.coachId }
            LessonModel(lesson, coach) }
    }

}