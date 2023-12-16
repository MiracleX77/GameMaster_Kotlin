package com.example.gamemaster.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun GameItem(game: Game,viewModel: GameViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable{
                viewModel.processIntent(GameViewIntent.DetailGame(game.id.toString()))
                      },

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = game.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(game.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = "Tags: ${game.genre}",fontSize = 20.sp, fontWeight = FontWeight.Medium,modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = game.platform,fontSize = 20.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.End,modifier = Modifier.align(
                    Alignment.CenterVertically))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


