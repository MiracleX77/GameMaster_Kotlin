package com.example.gamemaster.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun GameList(games: List<Game>,viewModel: GameViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(games) {
            GameItem(it,viewModel = viewModel)
        }
    }
}