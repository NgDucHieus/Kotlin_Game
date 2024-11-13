package com.example.eco.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eco.R

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
        topBar = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8FF))
                .padding(it) // Scaffold padding
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(12.dp))
            CategoryTabs()
            Spacer(modifier = Modifier.height(12.dp))
            CourseList()
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
            Icon(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "Back",
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )
        }
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            Text(

                text = "Video hướng dẫn",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7875FF),
            )
        }
    }
}

// Category Tabs
@Composable
fun CategoryTabs() {
    val selectedTab = remember { mutableStateOf("All") }
    val tabs = listOf("All", "Ongoing", "Completed")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabs.forEach { tab ->
            CategoryTab(
                title = tab,
                isSelected = selectedTab.value == tab
            ) {
                selectedTab.value = tab
            }
        }
    }
}

@Composable
fun CategoryTab(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFFAEA8F6) else Color(0xFFECEAFF),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
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

// Course List
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
        )
    )

    if (courses.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No courses available", fontSize = 16.sp, color = Color.Gray)
        }
    } else {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            courses.forEach { course ->
                CourseCard(course = course)
                Spacer(modifier = Modifier.height(16.dp))
            }
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
                        .height(10.dp),
                    color = Color(0xFF7875FF),
                    trackColor = Color(0xFFFFFFFF)
                )
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewMyCoursesScreen() {
    MyCoursesScreen()
}


@Composable
fun HeaderWithColorSeparation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8FF)) // Background color for the top section
    ) {
        Column {
            // First Row with Icon and Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFECEAFF)) // Separate color background
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle back click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector),
                        contentDescription = "Back",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "My Courses",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7875FF),
                )
                IconButton(onClick = { /* Handle search click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_course_image),
                        contentDescription = "Search",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            // Second Row with Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFAEA8F6)) // Another separate color
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CategoryTab(title = "All", isSelected = true, onClick = {})
                CategoryTab(title = "Ongoing", isSelected = false, onClick = {})
                CategoryTab(title = "Completed", isSelected = false, onClick = {})
            }
        }
    }
}

@Composable
fun CategoryTab2(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFF7875FF) else Color(0xFFECEAFF),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
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

@Preview(showBackground = true)
@Composable
fun PreviewHeaderWithColorSeparation() {
    HeaderWithColorSeparation()
}
