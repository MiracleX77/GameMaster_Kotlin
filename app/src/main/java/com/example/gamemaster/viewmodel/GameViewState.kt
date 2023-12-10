package com.example.gamemaster.viewmodel

import com.example.gamemaster.model.data.Game

sealed class GameViewState {
    data object Loading : GameViewState()
    data class Success(val games: List<Game>) : GameViewState()
    data class FilteredGames(val filteredGames: List<Game>) : GameViewState()
    data object Empty : GameViewState()
    data class  Error(val message: String) :GameViewState()
}