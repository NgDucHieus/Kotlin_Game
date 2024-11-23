package com.example.eco.screen.phase2

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.SnackbarDefaults.color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ECountingScreen(context: Context, onNavigateNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Instructional Text
        Text(
            text = "Hướng dẫn: Hãy kéo các đồng xu vào hộp tương ứng.",
            style = androidx.compose.material.MaterialTheme.typography.h6
        )

        // Audio Guide Button
        Button(onClick = { playAudioGuide(context) }) {
            Text("Nghe hướng dẫn")
        }

        // Mini-Game
        MiniGame()

        // Navigation Button
        Button(
            onClick = { onNavigateNext() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Tiếp tục")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun da()
{
    ECountingScreen(context = LocalContext.current ) {

    }
}