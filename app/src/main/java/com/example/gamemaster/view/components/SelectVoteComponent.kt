package com.example.gamemaster.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.ui.theme.GameMasterTheme
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun SelectVoteComponent(viewModel: GameViewModel, game1: Game,game2:Game,round:Int,n_round:Int) {
    Column {
        Spacer(Modifier.height(70.dp))
        Text(text = "Rounds of ${n_round*2} - $round/$n_round", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
        GameVoteItem(game1,viewModel)
        Text(text = "VS", fontSize = 40.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
        GameVoteItem(game2,viewModel)
    }

}
@Composable
fun GameVoteItem(game: Game,viewModel: GameViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.processIntent(GameViewIntent.SelectedVoteGame(game))
            },

        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = game.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(game.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, name = "GameVoteItem Preview")
@Composable
fun GameVoteItemPreview() {
    GameMasterTheme(darkTheme = true) {
        GameVoteItem(game = GameData.dummyGames[0],viewModel = GameViewModel())
    }
}

@Preview(showBackground = true, name = "SelectVoteComponent Preview")
@Composable
fun SelectVoteComponentPreview() {
    GameMasterTheme(darkTheme = true) {
        SelectVoteComponent(viewModel = GameViewModel(),game1 = GameData.dummyGames[0],game2 = GameData.dummyGames[1],1 , 1)
    }
}





