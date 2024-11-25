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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.example.eco.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Level3(onNextLevel: () -> Unit) {
    var showPopup by remember { mutableStateOf(true) } // Trạng thái hiển thị popup
    val oranges = remember { mutableStateListOf<Pair<Int, Boolean>>() } // Danh sách cam (true = đúng, false = sai)
    var score by remember { mutableStateOf(0) } // Điểm số
    val maxScore = 10
    val coroutineScope = rememberCoroutineScope()

    // Khởi tạo cam
    LaunchedEffect(showPopup) {
        if (!showPopup) {
            while (score < maxScore) {
                val isCorrect = (0..1).random() == 1
                oranges.add(Pair(oranges.size, isCorrect))

                // Xóa cam cũ sau 3 giây
                delay(3000L)
                if (oranges.isNotEmpty()) oranges.removeAt(0)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBDECB6)) // Màu nền xanh lá cây
    ) {
        // Popup hiển thị hướng dẫn cách chơi
        if (showPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x99000000)) // Nền đen mờ
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
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Bạn cần chọn đúng quả cam tươi để làm nước ép. " +
                                    "Nhấn vào cam tươi để ghi điểm. Tránh chọn nhầm cam không tươi nhé!",
                            fontSize = 16.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showPopup = false }, // Đóng popup và bắt đầu trò chơi
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Bắt đầu")
                        }
                    }
                }
            }
        } else {
            // Logic hiển thị cam và xử lý điểm
            oranges.forEach { (id, isCorrect) ->
                val xPosition = (50..300).random().dp
                val yPosition = (50..500).random().dp

                Image(
                    painter = painterResource(
                        if (isCorrect) R.drawable.orange1 else R.drawable.grape1
                    ),
                    contentDescription = if (isCorrect) "Cam tươi" else "Cam không tươi",
                    modifier = Modifier
                        .absoluteOffset(x = xPosition, y = yPosition)
                        .size(80.dp)
                        .clickable {
                            if (isCorrect) {
                                score++
                            } else {
                                coroutineScope.launch {
                                    delay(500L) // Phản hồi nhanh khi chọn sai
                                }
                            }
                            oranges.remove(Pair(id, isCorrect)) // Xóa cam sau khi nhấn
                        }
                )
            }

            // Hiển thị điểm
            Text(
                text = "Điểm: $score/$maxScore",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
            )

            // Nút hoàn thành cấp độ
            if (score >= maxScore) {
                Button(
                    onClick = onNextLevel,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Hoàn thành!")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPeekABooGameWithPopup() {
    Level3(onNextLevel = { println("Chuyển cấp độ!") })
}
