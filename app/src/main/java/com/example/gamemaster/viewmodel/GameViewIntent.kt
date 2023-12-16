package com.example.gamemaster.viewmodel

import com.example.gamemaster.model.data.Game

sealed class GameViewIntent {
    data object LoadAllGame: GameViewIntent()
    data class  SearchGame(val query:String): GameViewIntent()

    data class DetailGame(val id:String):GameViewIntent()
    data class  FilterGame(val type:String,val query: String) :GameViewIntent()
    data class StartVoteGame(val platforms:String,val tags:String,val round:String) :GameViewIntent()

    data class SelectedVoteGame(val game: Game):GameViewIntent()

    data object VoteGame:GameViewIntent()
}