package com.example.gamemaster.view

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.gamemaster.viewmodel.GameViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamemaster.view.components.BottomAppBar
import com.example.gamemaster.view.components.ErrorComponent
import com.example.gamemaster.view.components.GameDetail
import com.example.gamemaster.view.components.LoadingComponent
import com.example.gamemaster.view.components.ResultVoteComponent
import com.example.gamemaster.view.components.SelectVoteComponent
import com.example.gamemaster.view.components.SuccessComponent
import com.example.gamemaster.view.components.TopAppBar
import com.example.gamemaster.view.components.VoteComponent
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewState


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameListScreen(viewModel: GameViewModel = viewModel()) {
    val viewState by viewModel.viewState.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(viewModel)
        },
        bottomBar = {
            BottomAppBar(viewModel = viewModel)
        }
        
    ) {
        when (val state = viewState) {
            is GameViewState.Loading -> LoadingComponent()
            is GameViewState.Success -> {
                val games = state.games
                SuccessComponent(viewModel,games = games)
            }
            is GameViewState.FilteredGames -> {
                val filteredGames = state.filteredGames
                SuccessComponent(viewModel,games = filteredGames)
            }
            is GameViewState.DetailGames->{
                val gameDetail = state.game
                GameDetail(viewModel = viewModel, game_detail = gameDetail)
            }
            is GameViewState.VoteGame -> VoteComponent(viewModel = viewModel)
            is GameViewState.SelectVoteGame -> SelectVoteComponent(viewModel=viewModel, game1 = state.game1, game2 = state.game2, round = state.round, n_round = state.n_round)
            is GameViewState.ResultVoteGame -> ResultVoteComponent(viewModel = viewModel, game = state.game)
            is GameViewState.Empty -> EmptyScreen()
            is GameViewState.Error -> ErrorComponent(message = state.message,viewModel = viewModel)
            else -> {}
        }
    }
    LaunchedEffect(Unit) {
        viewModel.processIntent(GameViewIntent.LoadAllGame)
    }
}



@Composable
fun EmptyScreen() {
    // แสดง UI เมื่อไม่มีข้อมูล
    Text("No games available")
}




