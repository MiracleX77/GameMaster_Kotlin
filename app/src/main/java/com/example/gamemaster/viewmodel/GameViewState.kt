package com.example.gamemaster.viewmodel

import com.example.gamemaster.model.data.Game
import com.example.gamemaster.model.data.GameDetail

sealed class GameViewState {
    data object Loading : GameViewState()
    data class Success(val games: List<Game>) : GameViewState()
    data class FilteredGames(val filteredGames: List<Game>) : GameViewState()
    data class DetailGames(val game : GameDetail):GameViewState()
    data object Empty : GameViewState()
    data class  Error(val message: String) :GameViewState()
}