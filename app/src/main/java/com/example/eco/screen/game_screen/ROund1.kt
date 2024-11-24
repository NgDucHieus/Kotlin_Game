package com.example.eco.screen.game_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.eco.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Round1Screen(onComplete: () -> Unit) {
    var selectedFruit by remember { mutableStateOf<Fruit?>(null) }
    var completedFruits by remember { mutableStateOf(setOf<Fruit>()) }

    if (selectedFruit == null) {
        // Main fruit selection screen
        FruitSelectionScreen(
            completedFruits = completedFruits,
            onFruitSelected = { selectedFruit = it }
        )
    } else {
        // Individual fruit coloring instructions
        FruitInstructionScreen(
            fruit = selectedFruit!!,
            isCompleted = selectedFruit in completedFruits,
            onComplete = {
                completedFruits = completedFruits + selectedFruit!!
                selectedFruit = null
            }
        )
    }

    // "Complete Round" button
    if (completedFruits.size == fruits.size) {
        Button(
            onClick = { onComplete() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
        ) {
            Text(text = "Hoàn thành Vòng 1, chuyển sang Vòng 2", color = Color.White, fontSize = 16.sp)
        }
    }
}


@Composable
fun FruitInstructionScreen(
    fruit: Fruit,
    isCompleted: Boolean,
    onComplete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tô màu ${fruit.name}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = fruit.instructions,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = fruit.imageRes),
            contentDescription = fruit.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Fit,
            colorFilter = if (isCompleted) null else ColorFilter.tint(Color.Gray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onComplete() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
        ) {
            Text(text = "Bé đã tô xong", color = Color.White, fontSize = 16.sp)
        }
    }
}


@Composable
fun FruitSelectionScreen(
    completedFruits: Set<Fruit>,
    onFruitSelected: (Fruit) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bé tập làm họa sĩ") },
                backgroundColor = Color(0xFF57B4B4),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(fruits) { fruit ->
                ShakingBox(
                    fruit = fruit,
                    isCompleted = fruit in completedFruits,
                    onFruitSelected = onFruitSelected
                )
            }
        }
    }
}


@Composable
fun AnimatedBox(
    fruit: Fruit,
    isCompleted: Boolean,
    onFruitSelected: (Fruit) -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

    // Scale animation for click feedback
    val scale by animateFloatAsState(
        targetValue = if (isClicked) 0.9f else 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 200)
    )

    // Background color animation (optional)
    val backgroundColor by animateColorAsState(
        targetValue = if (isCompleted) Color(0xFFBBDEFB) else Color.White, // Light Blue if completed
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.4f) // Box takes up 40% of the parent's width
            .aspectRatio(1f) // Ensures the box is a square
            .scale(scale) // Apply the scale animation
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = backgroundColor, // Animated background color
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                isClicked = true // Trigger the animation
                onFruitSelected(fruit)
            },
        contentAlignment = Alignment.Center
    ) {
        // Reset the clicked state after the animation duration
        if (isClicked) {
            LaunchedEffect(Unit) {
                delay(300) // Wait for the animation duration
                isClicked = false
            }
        }

        Image(
            painter = painterResource(id = fruit.imageRes),
            contentDescription = fruit.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), // Space between the image and the box
            contentScale = ContentScale.Fit,
            colorFilter = if (isCompleted) null else ColorFilter.tint(Color.Gray) // Grayscale or colored
        )
    }
}




// Mock fruit data
data class Fruit(val name: String, val imageRes: Int, val instructions: String)

val fruits = listOf(
    Fruit("Quả Cam", R.drawable.orange1, "Hướng dẫn: Sử dụng màu cam để tô quả cam."),
    Fruit("Quả Táo", R.drawable.apple, "Hướng dẫn: Sử dụng màu đỏ để tô quả táo."),
    Fruit("Quả Chuối", R.drawable.banana, "Hướng dẫn: Sử dụng màu vàng để tô quả chuối."),
    Fruit("Quả Nho", R.drawable.grape1, "Hướng dẫn: Sử dụng màu tím để tô quả nho."),
    Fruit("Quả Dưa Hấu", R.drawable.watermelon, "Hướng dẫn: Sử dụng màu xanh và đỏ để tô dưa hấu."),
    Fruit("Quả Dâu", R.drawable.strawberry, "Hướng dẫn: Sử dụng màu đỏ và xanh lá cây để tô quả dâu."),
    Fruit("Quả Lê", R.drawable.pear, "Hướng dẫn: Sử dụng màu xanh nhạt để tô quả lê."),
    Fruit("Quả Chanh", R.drawable.lemon, "Hướng dẫn: Sử dụng màu vàng để tô quả chanh."),
)

@Preview(showBackground = true)
@Composable
fun PreviewRound1Screen() {
    Round1Screen(onComplete = { /* Do nothing for preview */ })
}

@Composable
fun ShakingBox(
    fruit: Fruit,
    isCompleted: Boolean,
    onFruitSelected: (Fruit) -> Unit
) {
    val scope = rememberCoroutineScope()

    // Position offset for the shake effect
    val offsetX = remember { Animatable(0f) }

    // Scale animation for click feedback
    val scale by animateFloatAsState(
        targetValue = if (offsetX.value != 0f) 0.95f else 1f, // Scale down during shake
        animationSpec = tween(durationMillis = 150)
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.4f) // Box takes up 40% of the parent's width
            .aspectRatio(1f) // Ensures the box is a square
            .scale(scale) // Apply the scale animation
            .offset(x = offsetX.value.dp) // Apply the horizontal shake effect
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = if (isCompleted) Color(0xFFBBDEFB) else Color.White, // Background changes when completed
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                scope.launch {
                    // Start the shake effect
                    repeat(3) {
                        offsetX.animateTo(
                            targetValue = -10f,
                            animationSpec = tween(durationMillis = 50)
                        )
                        offsetX.animateTo(
                            targetValue = 10f,
                            animationSpec = tween(durationMillis = 50)
                        )
                    }
                    offsetX.animateTo(0f, animationSpec = tween(durationMillis = 50)) // Reset position

                    // Add delay after the shake before triggering the next action
                    kotlinx.coroutines.delay(300) // Adjust delay duration here (300ms)

                    onFruitSelected(fruit) // Notify parent after delay
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = fruit.imageRes),
            contentDescription = fruit.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), // Add padding between the image and the box edges
            contentScale = ContentScale.Fit,
            colorFilter = if (isCompleted) null else ColorFilter.tint(Color.Gray) // Grayscale or colored
        )
    }
}
