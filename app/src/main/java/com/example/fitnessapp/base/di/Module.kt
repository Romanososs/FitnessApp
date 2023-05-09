package com.example.fitnessapp.base.di

import com.example.fitnessapp.feature.schedule.repository.ScheduleRepository
import com.example.fitnessapp.feature.schedule.repository.ScheduleRepositoryImpl
import com.example.fitnessapp.feature.schedule.service.ScheduleService
import com.example.fitnessapp.feature.schedule.service.ScheduleServiceImpl
import com.example.fitnessapp.feature.schedule.viewModel.ScheduleViewModel
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun koinModule() = module {

    viewModel(named("ScheduleViewModel")) {
        ScheduleViewModel()
    }
    single<ScheduleService> { ScheduleServiceImpl() }
    single<ScheduleRepository> { ScheduleRepositoryImpl() }

    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
    single<HttpClientEngine> {
        Android.create {}
    }
    single {
        HttpClient(get()) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}