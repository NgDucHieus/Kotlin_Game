package com.example.eco.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.roundToInt



@Composable
fun Level2(onNextLevel: () -> Unit, onFail: () -> Unit) {
    var orangesCaught by remember { mutableStateOf(0) }
    val basketYPosition = 600f // Fixed Y position for the basket
    var basketXPosition by remember { mutableStateOf(200f) } // Basket's initial X position
    val basketWidth = 150.dp.toPx() // Width of the basket
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }

    val treeXStart = 10 // Start X position for oranges
    val treeXEnd = 400 // End X position for oranges

    val oranges = remember { mutableStateListOf<Animatable<Offset, AnimationVector2D>>() }
    val processedOranges = remember { mutableStateMapOf<Animatable<Offset, AnimationVector2D>, Boolean>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (orangesCaught < 6) {
                val startX = (treeXStart..treeXEnd).random().toFloat()
                val animatableOffset = Animatable(
                    initialValue = Offset(startX, 0f),
                    typeConverter = Offset.VectorConverter
                )
                oranges.add(animatableOffset)
                processedOranges[animatableOffset] = false

                coroutineScope.launch {
                    val stepSize = 10f
                    while (animatableOffset.value.y < screenHeight) {
                        animatableOffset.snapTo(
                            animatableOffset.value.copy(
                                y = animatableOffset.value.y + stepSize
                            )
                        )
                        delay(16L)
                    }

                    // Remove oranges if out of screen
                    if (animatableOffset.value.y >= screenHeight) {
                        oranges.remove(animatableOffset)
                        processedOranges.remove(animatableOffset)
                    }
                }

                delay((1000..5000).random().toLong()) // Delay before next orange
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBDECB6)) // Light green background
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    basketXPosition = (basketXPosition + dragAmount.x)
                        .coerceIn(0f, screenWidth - basketWidth) // Limit within screen
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.orange_tree), // Replace with your drawable
            contentDescription = "Orange Tree",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(250.dp)
        )

        oranges.forEach { animatableOffset ->
            Image(
                painter = painterResource(id = R.drawable.orange1), // Replace with your orange drawable
                contentDescription = "Falling Orange",
                modifier = Modifier
                    .absoluteOffset(
                        x = animatableOffset.value.x.dp,
                        y = animatableOffset.value.y.dp
                    )
                    .size(50.dp)
            )

            LaunchedEffect(animatableOffset) {
                coroutineScope.launch {
                    while (animatableOffset.value.y < screenHeight) {
                        if (!processedOranges.getValue(animatableOffset) &&
                            isCaught(
                                orangeOffset = animatableOffset.value,
                                basketX = basketXPosition,
                                basketY = basketYPosition,
                                basketWidth = basketWidth
                            )
                        ) {
                            processedOranges[animatableOffset] = true
                            orangesCaught++
                            oranges.remove(animatableOffset)
                            break
                        }
                        delay(16L)
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.orange_basket), // Replace with your basket drawable
            contentDescription = "Basket",
            modifier = Modifier
                .absoluteOffset(x = basketXPosition.dp, y = basketYPosition.dp)
                .size(150.dp)
        )

        Text(
            text = "Caught: $orangesCaught/6",
            modifier = Modifier.align(Alignment.TopCenter),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        if (orangesCaught >= 6) {
            LaunchedEffect(Unit) {
                delay(1000L)
                onNextLevel()
            }
        }
    }
}

fun isCaught(
    orangeOffset: Offset,
    basketX: Float,
    basketY: Float,
    basketWidth: Float
): Boolean {
    val orangeSize = 50f // Diameter of the orange
    val basketHeight = 150f // Height of the basket
    val padding = 10f // Extra padding for accurate detection

    val isXAligned = orangeOffset.x + orangeSize > basketX - padding &&
            orangeOffset.x < basketX + basketWidth + padding

    val isYAligned = orangeOffset.y + orangeSize > basketY &&
            orangeOffset.y < basketY + basketHeight

    return isXAligned && isYAligned
}

@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { this@toPx.toPx() }

@Preview
@Composable
fun PreviewLevel2() {
    Level2(onNextLevel = { println("Next Level!") }, onFail = { println("Try Again!") })
}
