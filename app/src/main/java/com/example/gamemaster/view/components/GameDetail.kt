package com.example.gamemaster.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.model.data.GameDetail
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun GameDetail(viewModel: GameViewModel, game_detail:GameDetail ){
    Column {
        Spacer(Modifier.height(200.dp))
        Text(text = game_detail.title)
    }
}