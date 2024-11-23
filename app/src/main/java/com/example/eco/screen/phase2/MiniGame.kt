package com.example.eco.screen.phase2

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun MiniGame() {
    val itemPosition = remember { mutableStateOf(Offset.Zero) }
    val boxPosition = remember { mutableStateOf(Offset(200f, 500f)) }
    val isDropped = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Draggable item
        Box(
            modifier = Modifier
                .size(50.dp)
                .offset { IntOffset(itemPosition.value.x.toInt(), itemPosition.value.y.toInt()) }
                .background(Color.Blue)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        itemPosition.value += dragAmount
                        if (isOverlapping(itemPosition.value, boxPosition.value)) {
                            isDropped.value = true
                        }
                    }
                }
        )

        // Drop Target
        if (!isDropped.value) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset { IntOffset(boxPosition.value.x.toInt(), boxPosition.value.y.toInt()) }
                    .background(Color.Green)
            )
        }
    }
}

fun isOverlapping(item: Offset, target: Offset): Boolean {
    // Add logic to check overlap
    return false
}
