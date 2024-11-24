package com.example.eco.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco.R
import kotlinx.coroutines.delay
import kotlin.random.Random

import androidx.compose.material.Button
import androidx.compose.material.Text

@Composable
fun GoalBasedPeekabooGame() {
    var score by remember { mutableStateOf(0) }
    var visibleObjectIndex by remember { mutableStateOf(-1) }
    var remainingTime by remember { mutableStateOf(30) } // Game duration in seconds
    var isGameRunning by remember { mutableStateOf(true) }
    val objects = listOf(
        R.drawable.frog, // Replace with your drawable resources
        R.drawable.koala,
        R.drawable.chick,
        R.drawable.lion
    )

    var targetAnimal by remember { mutableStateOf(objects.random()) } // Random target animal
    val gridItems = (0..8).toList() // A 3x3 grid

    // Game timer and object display loop
    LaunchedEffect(isGameRunning) {
        while (isGameRunning && remainingTime > 0) {
            visibleObjectIndex = Random.nextInt(gridItems.size) // Random position in grid
            delay(1000L) // Object stays visible for 1 second
            visibleObjectIndex = -1 // Hide object
            delay(500L) // Pause before showing the next object
            remainingTime-- // Decrease game timer
        }
        isGameRunning = false // Stop game when timer runs out
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)), // Light beige background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Requested Animal
            Text(
                text = "Click on:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Image(
                painter = painterResource(id = targetAnimal),
                contentDescription = "Target Animal",
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Score and Timer
            Text(
                text = "Score: $score",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50) // Green color for score
            )
            Text(
                text = "Time Remaining: $remainingTime s",
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Game Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // 3x3 grid
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gridItems) { index ->
                    if (index == visibleObjectIndex) {
                        val displayedAnimal = objects.random()
                        Image(
                            painter = painterResource(id = displayedAnimal), // Random animal icon
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    if (displayedAnimal == targetAnimal) {
                                        score++ // Increment score if correct animal is clicked
                                        targetAnimal = objects.random() // Change the target animal
                                    }
                                    visibleObjectIndex = -1 // Hide the object
                                }
                        )
                    } else {
                        Spacer(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color(0xFFF5F5F5)) // Empty grid cell
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Restart Game Button
            Button(
                onClick = {
                    score = 0
                    remainingTime = 30
                    isGameRunning = true
                    targetAnimal = objects.random() // Reset target animal
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
            ) {
                Text(text = "Restart Game", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoalBasedPeekabooGame() {
    GoalBasedPeekabooGame()
}
