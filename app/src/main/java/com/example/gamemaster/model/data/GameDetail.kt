package com.example.gamemaster.model.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class GameDetail(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("thumbnail") val thumbnail: String,
    @SerialName("status") val status: String,
    @SerialName("short_description") val shortDescription: String,
    @SerialName("description") val description: String,
    @SerialName("game_url") val gameUrl: String,
    @SerialName("genre") val genre: String,
    @SerialName("platform") val platform: String,
    @SerialName("publisher") val publisher: String,
    @SerialName("developer") val developer: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("freetogame_profile_url") val freetogameProfileUrl: String,
    @SerialName("minimum_system_requirements") val minimumSystemRequirements: SystemRequirements,
    @SerialName("screenshots") val screenshots: List<Screenshot>
)

@Serializable
data class SystemRequirements(
    @SerialName("os") val os: String,
    @SerialName("processor") val processor: String,
    @SerialName("memory") val memory: String,
    @SerialName("graphics") val graphics: String,
    @SerialName("storage") val storage: String
)

@Serializable
data class Screenshot(
    @SerialName("id") val id: Int,
    @SerialName("image") val image: String
)
