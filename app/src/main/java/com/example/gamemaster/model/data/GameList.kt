package com.example.gamemaster.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameList (
    @SerialName("games") val games: List<Game>
    )