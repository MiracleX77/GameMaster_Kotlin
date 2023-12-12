package com.example.gamemaster.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun FilteredComponents(viewModel: GameViewModel,games: List<Game>){
    Column {
        Spacer(Modifier.height(70.dp))
        SearchAndFilterBar(viewModel)
        GameList(games = games)
    }
}