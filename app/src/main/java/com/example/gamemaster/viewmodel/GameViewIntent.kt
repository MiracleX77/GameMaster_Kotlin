package com.example.gamemaster.viewmodel

sealed class GameViewIntent {
    data object LoadAllGame: GameViewIntent()
    data class  SearchGame(val query:String): GameViewIntent()

    data class DetailGame(val id:String):GameViewIntent()
    data class  FilterGame(val type:String,val query: String) :GameViewIntent()
}