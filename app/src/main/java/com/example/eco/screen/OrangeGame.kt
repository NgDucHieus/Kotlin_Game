package com.example.eco.screen

import android.annotation.SuppressLint
import android.graphics.Color.rgb
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import androidx.compose.material.Text
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.pow

@Composable
fun OrangeGame() {
    var currentLevel by remember { mutableStateOf(1) }

    when (currentLevel) {
        1 -> Level1(onNextLevel = { currentLevel = 2 })
        2 -> Level2(onNextLevel = { currentLevel = 3 }, onFail = { currentLevel = 1 })
        3 -> Level3(onNextLevel = { currentLevel = 4 })
        4 -> Level4()
    }
}



@Composable
fun Level1(onNextLevel: () -> Unit) {
    var remainingTime by remember { mutableStateOf(10) } // Countdown timer (10 seconds)
    var seedPosition by remember { mutableStateOf(Offset(300f, 300f)) } // Initial seed position
    var isPlanted by remember { mutableStateOf(false) } // Has the seed been planted
    var isSeedInSoil by remember { mutableStateOf(false) } // Is the seed in the soil
    var isTimerRunning by remember { mutableStateOf(false) } // Is the timer running
    var buttonText by remember { mutableStateOf("Plant Seed") } // Button text

    // Soil center fixed at the middle of the screen
    val soilCenter = Offset(300f, 300f)

    // Tree animation for scaling effect
    val treeScale by animateFloatAsState(
        targetValue = if (isPlanted && remainingTime == 0) 1f else 0f,
        animationSpec = tween(durationMillis = 1000) // Smooth tree growth
    )

    // Calculate the distance between the seed and the soil center
    val distanceToSoil by remember {
        derivedStateOf {
            seedPosition.distanceTo(soilCenter)
        }
    }

    // Timer logic
    LaunchedEffect(isPlanted) {
        if (isPlanted && !isTimerRunning) {
            isTimerRunning = true
            while (remainingTime > 0) {
                delay(1000L) // 1-second interval
                remainingTime--
            }
            buttonText = "Thu hoạch cam nào" // Update button text
            isTimerRunning = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88A890)), // Greenish background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display countdown timer
            if (isPlanted) {
                Text(
                    text = "$remainingTime ", // Timer text
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Circular Soil Design
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .background(Color.Transparent, shape = CircleShape)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawCircle(
                        color = Color(rgb(154, 191, 128)), // Border color
                        radius = size.minDimension / 2, // Circle radius
                        style = Stroke(width = 50f) // Border stroke width
                    )
                    // Draw the green top half
                    drawArc(
                        color = Color(0xFFD6E4C3), // Light green top
                        startAngle = 180f, // Top center
                        sweepAngle = 180f, // Top half
                        useCenter = true
                    )

                    // Draw the brown bottom half (overlaps the top arc)
                    drawArc(
                        color = Color(0xFF8B4513), // Brown soil
                        startAngle = 0f, // Bottom center
                        sweepAngle = 180f, // Bottom half
                        useCenter = true
                    )
                }

                if (!isPlanted) {
                    // Seed
                    Image(
                        painter = painterResource(id = R.drawable.seed), // Replace with your seed drawable
                        contentDescription = "Seed",
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    seedPosition.x.roundToInt(),
                                    seedPosition.y.roundToInt()
                                )
                            }
                            .size(50.dp)
                            .pointerInput(Unit) {
                                detectDragGestures { _, dragAmount ->
                                    // Update seed position during drag
                                    seedPosition = Offset(
                                        seedPosition.x + dragAmount.x,
                                        seedPosition.y + dragAmount.y
                                    )
                                    // Check if the seed is in the soil
                                    isSeedInSoil = distanceToSoil < 100f
                                }
                            }
                    )
                } else {
                    // Tree appears after planting and countdown
                    Image(
                        painter = painterResource(id = R.drawable.orange_tree), // Replace with tree drawable
                        contentDescription = "Tree",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 70.dp) // Position tree above soil
                            .size((100.dp * treeScale).coerceAtLeast(0.dp))
                    )

                    // Navigate to the next level after planting
                    if (remainingTime == 0) {
                        LaunchedEffect(Unit) {
                            kotlinx.coroutines.delay(2000L) // Delay for 2 seconds
                            onNextLevel()
                        }
                    }
                }
            }

            // "Plant Seed" Button
            Button(
                onClick = {
                    if(remainingTime ==0)
                    {
                        onNextLevel()
                    }else{
                    isPlanted = true }
                          }, // Start planting
                enabled = isSeedInSoil, // Enable button only when the seed is in the soil
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSeedInSoil) Color(0xFF51962F) else Color.Gray
                ),
                modifier = Modifier.padding(top = 16.dp) // Space from the circle
            ) {
                Text(buttonText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display seed position
//            Text(
//                text = "Seed Position: (${seedPosition.x.roundToInt()}, ${seedPosition.y.roundToInt()})",
//                fontSize = 16.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(top = 8.dp)
//            )
        }
    }
}

// Extension function to calculate the distance between two points
private fun Offset.distanceTo(other: Offset): Float {
    val dx = x - other.x
    val dy = y - other.y
    return (dx * dx + dy * dy).pow(0.5f)
}








// Level 3: Preparing juices


// Level 4: Garden decoration
@Preview(showBackground = true)
@Composable
fun PreviewOrangeGame() {
    OrangeGame()
}


@Preview(showBackground = true)
@Composable
fun PreviewLevel1() {
    Level1(onNextLevel = { /* Navigate to Level 2 */ })
    println("Seed Position:" )
    println("Is Seed in Soil:")

}

