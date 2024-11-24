package com.example.eco.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.tooling.preview.Preview
import com.example.eco.R

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF6F6F6))
    ) {
        // Top Section
        DiscoverHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Options
        MenuOptions()

        Spacer(modifier = Modifier.height(16.dp))

        // Exercise Section
        ExerciseSection()
    }
}

@Composable
fun DiscoverHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp)
    ) {
        Text(
            text = "Khám phá những điều thú vị",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4B3F34)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color(0xFFE4D9C9), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Level Badge
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFDFAE4F), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "4",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    // Greeting Text
                    Column {
                        Text(
                            text = "Chào Híu",
                            fontSize = 16.sp,
                            color = Color(0xFF4B3F34),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "60%",
                            fontSize = 14.sp,
                            color = Color(0xFF947F62)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MenuOption("Chủ đề", R.drawable.app)
        MenuOption("Tìm kiếm", R.drawable.loupe)
        MenuOption("Lịch", R.drawable.calendar)
        MenuOption("Bảng xếp hạng", R.drawable.podium)
    }
}

@Composable
fun MenuOption(label: String, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color(0xFFF3EDE4), shape = CircleShape), // Light beige background
            contentAlignment = Alignment.Center
        ) {
            // Use Image instead of Icon to preserve original drawable color
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(30.dp) // Adjust size to fit inside the circle
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF4B3F34), // Dark brown text color
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun ExerciseSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Exercise",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B3F34)
            )
            Text(
                text = "See More",
                fontSize = 14.sp,
                color = Color(0xFFDFAE4F)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ExerciseCard(
                title = "Angkat Beban",
                description = "Latihan angkat beban untuk meningkatkan otot",
                iconRes = R.drawable.feather,
                backgroundColor = Color(0xFFFDDCBA)
            )
            ExerciseCard(
                title = "Maraton",
                description = "Latihan cukup 100m untuk meningkatkan kapasitas",
                iconRes = R.drawable.winner,
                backgroundColor = Color(0xFFCDEAC0)
            )
        }
    }
}

@Composable
fun ExerciseCard(title: String, description: String, iconRes: Int, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            // Exercise Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color(0xFFDFAE4F),
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF4B3F34)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color(0xFF4B3F34)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle action */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDFAE4F)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Let's Go", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
