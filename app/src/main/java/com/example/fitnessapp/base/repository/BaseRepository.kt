package com.example.fitnessapp.base.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository : KoinComponent {
    private val httpClient: HttpClient by inject()
    protected val json: Json by inject()
    private val BASE_API = "https://olimpia.fitnesskit-admin.ru"

    protected suspend fun executeCall(
        type: HttpMethod,
        path: String,
        parameters: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        body: String? = null,
    ): String {
        val response = httpClient.request(BASE_API) {
            url {
                appendPathSegments(path)
                parameters?.forEach {
                    this.parameters.append(it.key, it.value)
                }
            }
            method = type
            headers?.forEach { this.headers.append(it.key, it.value) }
            body?.let { setBody(it) }
        }
        return response.body<String>()
    }
}