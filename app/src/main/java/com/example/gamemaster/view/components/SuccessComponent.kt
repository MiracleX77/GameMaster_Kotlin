package com.example.gamemaster.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import com.example.gamemaster.model.data.Game
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel


@Composable
fun SuccessComponent(viewModel: GameViewModel,games: List<Game>) {
    Column {
        Spacer(Modifier.height(70.dp))
        SearchAndFilterBar(viewModel = viewModel)
        GameList(games = games,viewModel = viewModel)
    }
}


@Preview(showBackground = true, name = "SuccessComponent Preview")
@Composable
fun SuccessComponentPreview() {
    MaterialTheme {
        SuccessComponent(viewModel = GameViewModel(), games = GameData.dummyGames)
    }
}
