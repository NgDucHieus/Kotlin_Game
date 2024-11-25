package com.example.eco.screen


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.roundToInt


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco.R

@Composable
fun Level4() {
    // Danh sách biểu tượng
    val decorationOptions = listOf(
        R.drawable.orange_tree,  // Cây cam
        R.drawable.bench,        // Ghế
        R.drawable.butterfly,    // Bướm
        R.drawable.bird,         // Chim
        R.drawable.character1,   // Nhân vật 1
        R.drawable.character1,   // Nhân vật 2
        R.drawable.sun,          // Mặt trời
        R.drawable.cloud         // Mây
    )

    // Danh sách biểu tượng đã đặt
    val placedDecorations = remember { mutableStateListOf<Pair<Int, Offset>>() }

    // Trạng thái hiển thị popup
    var showInstruction by remember { mutableStateOf(true) }

    // Trạng thái mũi tên
    var currentIconIndex by remember { mutableStateOf(0) }

    // Trạng thái kéo
    var draggingItem by remember { mutableStateOf<Int?>(null) }
    var draggingPosition by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD2B48C)) // Nền bãi đất
    ) {
        // Hiển thị popup hướng dẫn nếu `showInstruction = true`
        if (showInstruction) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x99000000)) // Nền bán trong suốt
            ) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(32.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Hướng dẫn cách chơi",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Dùng các nút mũi tên để chọn biểu tượng. " +
                                    "Kéo và thả biểu tượng lên màn hình để trang trí vườn cam. " +
                                    "Bạn có thể đặt cây cam, ghế, bướm, chim, và nhiều biểu tượng khác!",
                            fontSize = 16.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showInstruction = false }, // Đóng hướng dẫn và bắt đầu chơi
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Bắt đầu")
                        }
                    }
                }
            }
        } else {
            // Hiển thị các biểu tượng đã đặt
            placedDecorations.forEach { (drawableRes, offset) ->
                Image(
                    painter = painterResource(id = drawableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .absoluteOffset(offset.x.dp, offset.y.dp)
                        .size(80.dp)
                )
            }

            // Hiển thị biểu tượng đang kéo
            if (draggingItem != null) {
                Image(
                    painter = painterResource(id = draggingItem!!),
                    contentDescription = null,
                    modifier = Modifier
                        .absoluteOffset(draggingPosition.x.dp, draggingPosition.y.dp)
                        .size(80.dp)
                )
            }

            // Nút mũi tên trái
            Button(
                onClick = {
                    if (currentIconIndex > 0) currentIconIndex--
                },
                enabled = currentIconIndex > 0, // Vô hiệu hóa nếu đang ở biểu tượng đầu tiên
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text("<")
            }

            // Nút mũi tên phải
            Button(
                onClick = {
                    if (currentIconIndex < decorationOptions.size - 1) currentIconIndex++
                },
                enabled = currentIconIndex < decorationOptions.size - 1, // Vô hiệu hóa nếu đang ở biểu tượng cuối cùng
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(">")
            }

            // Biểu tượng hiện tại (giữa màn hình)
            val currentIcon = decorationOptions[currentIconIndex]
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .size(80.dp)
                    .background(Color.White, shape = CircleShape)
                    .pointerInput(currentIcon) {
                        detectDragGestures(
                            onDragStart = {
                                draggingItem = currentIcon // Lấy icon hiện tại
                                draggingPosition = Offset(it.x, it.y)
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                draggingPosition = draggingPosition.copy(
                                    x = draggingPosition.x + dragAmount.x,
                                    y = draggingPosition.y + dragAmount.y
                                )
                            },
                            onDragEnd = {
                                placedDecorations.add(
                                    Pair(
                                        draggingItem!!,
                                        Offset(
                                            draggingPosition.x.coerceIn(0f, 300f),
                                            draggingPosition.y.coerceIn(0f, 500f)
                                        )
                                    )
                                )
                                draggingItem = null // Dừng kéo
                            }
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = currentIcon),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLevel4DecorateGardenWithInstructionAndArrows() {
    Level4()
}
