package com.example.eco.screen.game_screen

import android.app.GameState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Level1Screen(viewModel: GameState, onLevelUp: () -> Unit) {
    val isSeedPlanted = viewModel.isSeedPlanted

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.soil_background),
            contentDescription = "Soil Background",
            modifier = Modifier.fillMaxSize()
        )

        // Seed Icon
        if (!isSeedPlanted.value) {
            DraggableSeed { isPlanted ->
                if (isPlanted) {
                    viewModel.isSeedPlanted.value = true
                    onLevelUp() // Advance to the next level
                }
            }
        } else {
            // Show growing tree animation or static
            Image(
                painter = painterResource(id = R.drawable.growing_tree),
                contentDescription = "Growing Tree",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@Composable
fun DraggableSeed(onDrop: (Boolean) -> Unit) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val soilArea = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.seed),
            contentDescription = "Seed",
            modifier = Modifier
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                        soilArea.value = isOverSoil(offsetX, offsetY)
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        if (soilArea.value) {
                            onDrop(true)
                        }
                    })
                }
        )
    }
}

fun isOverSoil(x: Float, y: Float): Boolean {
    // Define soil area boundaries
    return x > 300f && x < 700f && y > 800f && y < 1200f
}
