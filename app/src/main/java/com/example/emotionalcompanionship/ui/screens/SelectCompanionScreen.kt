package com.example.emotionalcompanionship.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectCompanionScreen(
    onCompanionSelected: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "选择已有陪伴人",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(8) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f),
                    onClick = onCompanionSelected
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // 这里后续会添加头像图片
                        Text("陪伴人 ${index + 1}")
                    }
                }
            }
        }

        Button(
            onClick = { /* TODO: 添加新陪伴人 */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "确认选择",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
} 