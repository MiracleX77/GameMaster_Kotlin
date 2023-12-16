package com.example.gamemaster.view.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoteComponent(viewModel: GameViewModel) {
    val context = LocalContext.current
    val platforms = listOf("all", "pc", "browser")
    val tags = listOf(
        "mmorpg",
        "shooter",
        "strategy",
        "moba",
        "racing",
        "sports",
        "open-world",
        "survival",
        "turn-based",
        "battle-royale",
        "fantasy",
        "action-rpg"
    )
    val round = listOf("16", "32", "64")
    var expanded_platforms by remember { mutableStateOf(false) }
    var expanded_tags by remember { mutableStateOf(false) }
    var expanded_round by remember { mutableStateOf(false) }
    var selected_platforms by remember { mutableStateOf("all") }
    var selected_tags by remember { mutableStateOf("all") }
    var selected_round by remember { mutableStateOf("64") }

    Column {
        Spacer(Modifier.height(130.dp))
        Text(
            text = "VoteBattleRoyal",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 30.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Select Platforms:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded_platforms,
                onExpandedChange = { expanded_platforms = !expanded_platforms },
                modifier = Modifier.align(Alignment.Center)
            ) {
                TextField(
                    value = selected_platforms,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded_platforms) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded_platforms,
                    onDismissRequest = { expanded_platforms = false }
                ) {
                    platforms.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selected_platforms = item
                                expanded_platforms = false
                            }
                        )
                    }
                }
            }
        }
        Text(
            text = "Select Tags:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded_tags,
                onExpandedChange = { expanded_tags = !expanded_tags },
                modifier = Modifier.align(Alignment.Center)
            ) {
                TextField(
                    value = selected_tags,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded_tags) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded_tags,
                    onDismissRequest = { expanded_tags = false }
                ) {
                    tags.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selected_tags = item
                                expanded_tags = false
                            }
                        )
                    }
                }
            }
        }
        Text(
            text = "Select Rounds:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded_round,
                onExpandedChange = { expanded_round = !expanded_round },
                modifier = Modifier.align(Alignment.Center)
            ) {
                TextField(
                    value = "Round of $selected_round",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded_round) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded_round,
                    onDismissRequest = { expanded_round = false }
                ) {
                    round.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = "Round of $item") },
                            onClick = {
                                selected_round = item
                                expanded_round = false

                            }
                        )
                    }
                }
            }
        }
        FilledTonalButton(
            onClick = {  viewModel.processIntent(GameViewIntent.StartVoteGame(
                platforms=selected_platforms,
                tags=selected_tags,
                round=selected_round
            ))},
            modifier = Modifier
                .padding(vertical = 20.dp)
                .align(Alignment.CenterHorizontally)
        )
        {
            Text(text = "Start", fontSize = 20.sp)
        }
    }
}