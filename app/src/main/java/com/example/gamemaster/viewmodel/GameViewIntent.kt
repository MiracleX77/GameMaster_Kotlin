package com.example.gamemaster.viewmodel

sealed class GameViewIntent {
    data object LoadAllGame: GameViewIntent()
}