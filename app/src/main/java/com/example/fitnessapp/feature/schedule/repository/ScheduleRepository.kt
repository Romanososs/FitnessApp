package com.example.fitnessapp.feature.schedule.repository

import com.example.fitnessapp.base.repository.BaseRepository
import com.example.fitnessapp.feature.schedule.model.ScheduleResponse
import io.ktor.http.*

interface ScheduleRepository {
    suspend fun getSchedule(): ScheduleResponse
}

class ScheduleRepositoryImpl : BaseRepository(), ScheduleRepository {
    override suspend fun getSchedule(): ScheduleResponse {
        val response =
            executeCall(HttpMethod.Get, "schedule/get_v3", parameters = mapOf("club_id" to "2"))
        return json.decodeFromString(ScheduleResponse.serializer(), response)
    }

}