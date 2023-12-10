package com.example.gamemaster.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.gamemaster.view.components.GameItem
import com.example.gamemaster.viewmodel.GameViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamemaster.view.components.LoadingComponent
import com.example.gamemaster.view.components.SuccessComponent
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewState


@Composable
fun GameListScreen(viewModel: GameViewModel = viewModel()) {
    // สังเกต LiveData และแปลงเป็น State
    val viewState by viewModel.viewState.observeAsState()

    when (val state = viewState){
        is GameViewState.Loading -> LoadingComponent()
        is GameViewState.Success -> {
            val games = state.games
            SuccessComponent(games = games)
        }
        is GameViewState.Empty -> EmptyScreen()
        is GameViewState.Error -> ErrorScreen(message = state.message)
        else -> {}
    }
    LaunchedEffect(Unit){
        viewModel.processIntent(GameViewIntent.LoadAllGame)
    }
}



@Composable
fun EmptyScreen() {
    // แสดง UI เมื่อไม่มีข้อมูล
    Text("No games available")
}

@Composable
fun ErrorScreen(message: String) {
    // แสดงข้อความผิดพลาด
    Text("Error: $message")
}


