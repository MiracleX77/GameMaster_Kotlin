package com.example.gamemaster.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.model.data.GameDetail
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GameDetail(viewModel: GameViewModel, game_detail:GameDetail ){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item{
            Spacer(Modifier.height(50.dp))
        }
        item {
            Image(
                painter = rememberAsyncImagePainter(game_detail.thumbnail),
                contentDescription = "Game Thumbnail",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
        }
        item {
            Text(
                text = game_detail.title,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Text(
                text = "Developer: ${game_detail.developer}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(text = "Publisher: ${game_detail.publisher}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(text = "ReleaseDate: ${game_detail.releaseDate}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Spacer(Modifier.height(20.dp))
            Text(
                text = game_detail.shortDescription,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text =  "Tags: ${game_detail.genre}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(game_detail.screenshots) { screenshot ->
            Image(
                painter = rememberAsyncImagePainter(screenshot.image),
                contentDescription = "Screenshot",
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item{
            Text(
                text =  "Description:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
            )

            Text(
                text =  game_detail.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(30.dp))
        }
        item {
            Text(
                text =  "Minimum System Requirements:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
            )
            Text(
                text =  "Os : ${game_detail.minimumSystemRequirements.os}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Text(
                text =  "Processor : ${game_detail.minimumSystemRequirements.processor}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Text(
                text =  "Memory : ${game_detail.minimumSystemRequirements.memory}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Text(
                text =  "Graphics : ${game_detail.minimumSystemRequirements.graphics}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Text(
                text =  "Storage : ${game_detail.minimumSystemRequirements.storage}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true, name = "GameDetail Preview")
@Composable
fun GameDetailPreview() {
    MaterialTheme {
        GameDetail(viewModel = GameViewModel(), game_detail = GameData.dummyDetail)
    }
}
