package com.example.eco.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eco.R

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round

@Composable
fun MoviesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Movies Screen", fontSize = 24.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun VideoScreen()
{
    MoviesScreen()
}




// Data Model for Courses
data class Course(
    val title: String,
    val description: String,
    val progress: Float,
    val backgroundColor: Color
)

// Main Screen
@Composable
fun MyCoursesScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar2() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8FF))
                .padding(paddingValues)
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            CategoryTabs()
            Spacer(modifier = Modifier.height(16.dp))
//            PromotionBanner()
            Spacer(modifier = Modifier.height(16.dp))
            CourseList() // Dynamic list of courses
        }
    }
}

// Header Section
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /* Handle back click */ }) {
            Icon(painter = painterResource(id = R.drawable.vector),
                contentDescription = "Back",
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "Video hướng dẫn", fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7875FF),

                )
        }

    }
}

// Category Tabs
@Composable
fun CategoryTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CategoryTab("All", true)
        CategoryTab("Ongoing", false)
        CategoryTab("Completed", false)
    }
}

@Composable
fun CategoryTab(title: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFFAEA8F6) else Color(0xFFECEAFF),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { /* Handle tab click */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = if (isSelected) Color.White else Color(0xFF4A4A8A),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun CourseList() {
    val courses = listOf(
        Course(
            title = "UI/UX Designing",
            description = "Design intuitive digital interfaces for a seamless user experience...",
            progress = 70f,
            backgroundColor = Color(0xFFBFFFE0)
        ),
        Course(
            title = "Cyber Security",
            description = "Dive into the realm of digital defenses and protect systems from threats...",
            progress = 60f,
            backgroundColor = Color(0xFFFFF9C1)
        ),
        Course(
            title = "Graphic Designing",
            description = "Unleash your creative potential in digital design and create stunning visuals...",
            progress = 80f,
            backgroundColor = Color(0xFFFFD5D5)
        ),
        Course(
            title = "Graphic Designing",
            description = "Unleash your creative potential in digital design and create stunning visuals...",
            progress = 80f,
            backgroundColor = Color(0xFFFFD5D5)
    )
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        courses.forEach { course ->
            CourseCard(course = course)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Course Card
@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = course.backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Section: Illustration
            Image(
                painter = painterResource(id = R.drawable.ic_course_image), // Replace with your illustration
                contentDescription = "Course Illustration",
                modifier = Modifier
                    .size(120.dp)
                    .weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Right Section: Text and Progress
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Title
                Text(
                    text = course.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A4A8A)
                )

                // Description
                Text(
                    text = course.description,
                    fontSize = 14.sp,
                    color = Color(0xFF4A4A8A)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Information
                Text(
                    text = "Completed ${course.progress.toInt()}%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF4A4A8A)
                )

                // Progress Bar
                LinearProgressIndicator(
                    progress = course.progress / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .height(10.dp)
                    ,

                    color = Color(0xFF7875FF),
                    trackColor = Color(0xFFFFFFFF)

                )

            }
        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar2() {
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_home),tint = Color.Unspecified, contentDescription = "Home") },
            selected = true,
            onClick = { /* Handle home click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_movies),tint = Color.Unspecified, contentDescription = "Favorites") },
            selected = false,
            onClick = { /* Handle favorites click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_books),tint = Color.Unspecified, contentDescription = "Courses") },
            selected = false,
            onClick = { /* Handle courses click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_books),tint = Color.Unspecified, contentDescription = "Notifications") },
            selected = false,
            onClick = { /* Handle notifications click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_course_image),tint = Color.Unspecified, contentDescription = "Search") },
            selected = false,
            onClick = { /* Handle search click */ }
        )
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewMyCoursesScreen() {
    MyCoursesScreen()
}

