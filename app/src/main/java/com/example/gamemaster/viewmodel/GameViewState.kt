package com.example.gamemaster.viewmodel

import com.example.gamemaster.model.data.Game
import com.example.gamemaster.model.data.GameDetail

sealed class GameViewState {
    data object Loading : GameViewState()
    data class Success(val games: List<Game>) : GameViewState()
    data class FilteredGames(val filteredGames: List<Game>) : GameViewState()
    data class DetailGames(val game : GameDetail):GameViewState()

    data object VoteGame: GameViewState()
    data class SelectVoteGame(val game1 :Game,val game2 :Game,val round:Int,val n_round:Int): GameViewState()
    data class ResultVoteGame(val game:Game):GameViewState()
    data object Empty : GameViewState()
    data class  Error(val message: String) :GameViewState()
}