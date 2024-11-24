package com.example.eco.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eco.R

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "activity_screen") {
        composable("activity_screen") {
            ActivityScreen(navController = navController)
        }
        composable("peekaboo_game") {
            GoalBasedPeekabooGame()
        }
    }
}

@Composable
fun ActivityScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF89CFF0), // Light sky blue
                        Color(0xFFB3E5FC) // Pale blue
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top section with decorations
            Spacer(modifier = Modifier.height(16.dp))
            TopDecorations()
            Spacer(modifier = Modifier.height(16.dp))

            // Activity Cards
            ActivityCard(
                title = "The Alphabet",
                subtitle = "11 từ còn lại",
                iconRes = R.drawable.abc, // Replace with your drawable
                backgroundColor = Color(0xFFFDD835) // Yellow
            ) {
                // Handle navigation if needed
            }
            Spacer(modifier = Modifier.height(16.dp))
            ActivityCard(
                title = "Trò chơi trí tuệ",
                subtitle = "7 Câu đố",
                iconRes = R.drawable.cat, // Replace with your drawable
                backgroundColor = Color(0xFF7986CB) // Light Purple
            ) {
                navController.navigate("peekaboo_game") // Navigate to Peekaboo game
            }
        }
    }
}

@Composable
fun TopDecorations() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.orange1), // Replace with your drawable
            contentDescription = "Top Decorations",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ActivityCard(
    title: String,
    subtitle: String,
    iconRes: Int,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(120.dp)
            .clickable { onClick() }, // Make the card clickable
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes), // Replace with your drawable
                    contentDescription = title,
                    modifier = Modifier.size(60.dp)
                )
            }

            // Title and Subtitle
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color(0xFFFFF9C4)
                )
            }

            // Arrow
            Icon(
                painter = painterResource(id = R.drawable.right_arrow), // Replace with your drawable
                contentDescription = "Arrow",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainApp() {
    MainApp()
}
