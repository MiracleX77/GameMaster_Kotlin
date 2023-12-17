package com.example.gamemaster.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamemaster.ui.theme.GameMasterTheme
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(viewModel: GameViewModel){
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar (
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.onPrimary
    ){
        NavigationBarItem(selected = selectedItem == 0
            , onClick = {
                selectedItem = 0
                viewModel.processIntent(GameViewIntent.LoadAllGame)
                        }
            , icon = { Icon(Icons.Filled.Home,contentDescription = "home") }
            , label = {Text("Home")}
        )
        NavigationBarItem(selected = selectedItem == 1
            , onClick = {
                selectedItem = 1
                viewModel.processIntent(GameViewIntent.VoteGame)
                        }
            , icon = { Icon(Icons.Filled.Share,contentDescription = "vs") }
            , label = {Text("Vote Battle")}
        )

    }
}

@Preview(showBackground = true, name = "BottomAppBar Preview")
@Composable
fun BottomAppBarPreview() {
    GameMasterTheme(darkTheme = true) {
        BottomAppBar(viewModel = GameViewModel())
    }
}
