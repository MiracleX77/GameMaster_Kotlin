package com.example.gamemaster.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamemaster.ui.theme.GameMasterTheme
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun LoadingComponent(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Loading..")
    }
}

@Preview(showBackground = true, name = "LoadingComponent Preview")
@Composable
fun LoadingComponentPreview() {
    GameMasterTheme(darkTheme = true) {
        LoadingComponent()
    }
}