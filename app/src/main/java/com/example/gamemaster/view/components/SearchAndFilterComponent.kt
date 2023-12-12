package com.example.gamemaster.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamemaster.viewmodel.GameViewIntent
import com.example.gamemaster.viewmodel.GameViewModel

@Composable
fun SearchAndFilterBar(viewModel: GameViewModel) {
    var searchText by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedPlatform by remember { mutableStateOf("all") }
    val platforms = listOf("all", "pc", "browser")
    val tags = listOf("mmorpg","shooter","strategy","moba","racing","sports","open-world","survival","turn-based","battle-royale","fantasy","action-rpg")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 5.dp)
    ) {
        // ปุ่ม Sort
        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
            TextButton(onClick = { expanded = true }) {
                Text("Filter")
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Select Filter")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Text("Platform", modifier = Modifier.padding(vertical = 4.dp))
                Divider()
                platforms.forEach { platform ->
                    // Custom item
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedPlatform = platform
                                expanded = false
                                viewModel.processIntent(GameViewIntent.FilterGame("platform", platform))
                            }
                            .padding(vertical = 3.dp)  // Reduced padding
                    ) {
                        Text(text = platform, modifier = Modifier.padding(start = 16.dp))
                    }
                }
                Divider()
                Text("Tags", modifier = Modifier.padding(vertical = 4.dp))
                Divider()
                tags.forEach { tags ->
                    // Custom item
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedPlatform = tags
                                expanded = false
                                viewModel.processIntent(GameViewIntent.FilterGame("tags", tags))
                            }
                            .padding(vertical = 3.dp)  // Reduced padding
                    ) {
                        Text(text = tags, modifier = Modifier.padding(start = 16.dp))
                    }
                }
            }
        }

        Spacer(Modifier.width(8.dp)) // ระยะห่างระหว่างปุ่มและแถบค้นหา

        // แถบค้นหา
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Search") },
            modifier = Modifier
                .weight(4f)
                .height(50.dp), // กำหนดความสูงที่ชัดเจน
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { viewModel.processIntent(GameViewIntent.SearchGame(searchText)) }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            }
        )

        Spacer(Modifier.width(8.dp)) // ระยะห่างระหว่างแถบค้นหาและปุ่ม

        TextButton(
            onClick = { /* Handle sort */ },
            modifier = Modifier.weight(1f),
            // ปรับปุ่มให้มีพื้นหลังและมุมที่มน
            shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
            contentPadding = PaddingValues()
        ) {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Sort")
            Text("Sort")
        }

    }
}

@Composable
fun DropdownMenuCustomItem(
    text: String,
    onSelect: () -> Unit
) {
    TextButton(onClick = onSelect, contentPadding = PaddingValues(1.dp)) {
        Text(text)
    }
}