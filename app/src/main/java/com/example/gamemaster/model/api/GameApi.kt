package com.example.gamemaster.model.api

import com.example.gamemaster.model.data.Game
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object GameApi {
    private const val BASE_URL = "https://www.freetogame.com/api/games"

    private val apiClient = HttpClient(Android){
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun getAllGame(): List<Game>{
        val url = BASE_URL
        val response: HttpResponse = apiClient.get(url)
        return Json.decodeFromString(ListSerializer(Game.serializer()), response.bodyAsText())
    }
}