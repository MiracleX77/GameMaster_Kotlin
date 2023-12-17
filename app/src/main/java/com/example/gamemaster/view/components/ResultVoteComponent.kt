package com.example.gamemaster.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel
import com.example.gamemaster.viewmodel.GameViewState

@Composable
fun ResultVoteComponent(viewModel: GameViewModel, game: Game) {
    Column {
        Spacer(Modifier.height(130.dp))
        Text(text = "Best Game in Battle", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier
            .align(
                Alignment.CenterHorizontally
            )
            .padding(horizontal = 50.dp))
        Spacer(modifier = Modifier.height(30.dp))
        GameVoteItem(game,viewModel)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedButton(onClick = { viewModel.processIntent(GameViewIntent.LoadAllGame) }
            ,modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 50.dp)
        )
         {
             Text("Confirm")
        }
    }

}