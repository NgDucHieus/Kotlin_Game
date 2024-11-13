package com.example.eco.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import kotlinx.coroutines.launch
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box

//@Composable
//fun MainScreen() {
//    // Example main screen content
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Welcome to the Main Screen!",
//            style = MaterialTheme.typography.headlineMedium
//        )
//    }
//}
@Preview (backgroundColor = 0xFFFFFFFF)
@Composable
fun MainScreenPreview()
{
    MainScreen()
}

@Preview
@Composable
fun reviewMain()
{
    AppNavigation()
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("course") { CourseDetailScreen(navController) }
        composable("profile") { ProfileScreen() }
    }
}
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFDFD))
            .padding(16.dp)
    ) {
        Text(
            text = "Course",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CourseCard(
            title = "Children Drawing Course",
            level = "Level 1-3",
            onJoinClick = { navController.navigate("course") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CourseCard(
            title = "Creative Play Course",
            level = "Level 2-4",
            onJoinClick = { navController.navigate("course") }
        )
    }
}

@Composable
fun CourseCard(title: String, level: String, onJoinClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.google), // Replace with your image
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = level, fontSize = 14.sp, color = Color.Gray)
                Button(
                    onClick = onJoinClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF386FFF))
                ) {
                    Text(text = "JOIN", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CourseDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Children Drawing Course",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LessonCard(lessonName = "Lesson 1", duration = "45:00", onPlayClick = {})
        Spacer(modifier = Modifier.height(8.dp))
        LessonCard(lessonName = "Lesson 2", duration = "36:42", onPlayClick = {})
    }
}

@Composable
fun LessonCard(lessonName: String, duration: String, onPlayClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(text = lessonName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = duration, fontSize = 14.sp, color = Color.Gray)
            }
            Button(
                onClick = onPlayClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF386FFF))
            ) {
                Text(text = "Play", color = Color.White)
            }
        }
    }
}

//@Composable
//fun ProfileScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.google), // Replace with your image
//            contentDescription = "Profile Picture",
//            modifier = Modifier.size(100.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(text = "Arlene Fox", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//        Text(text = "Score: 3642", fontSize = 16.sp, color = Color.Gray)
//        Text(text = "Joined: 2020-08-30", fontSize = 14.sp, color = Color.Gray)
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        MenuOption("Ability Level Test")
//        MenuOption("Offline Download")
//        MenuOption("Help and Information")
//        MenuOption("Contact Us")
//    }
//}

@Composable
fun MenuOption(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(16.dp)
    )
}

@Composable
fun CourseScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8F8F8))
                .padding(16.dp)
        ) {
            Header()
            Spacer(modifier = Modifier.height(16.dp))
            CourseCard()
            Spacer(modifier = Modifier.height(16.dp))
            LessonCard(lessonTitle = "Lesson 1", time = "AM 9:42", imageRes = R.drawable.google)
            Spacer(modifier = Modifier.height(8.dp))
            LessonCard(lessonTitle = "Lesson 2", time = "PM 3:42", imageRes = R.drawable.google)
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Course",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        IconButton(onClick = { /* Handle actions */ }) {
            Icon(
                painter = painterResource(id = R.drawable.google), // Replace with your settings icon
                contentDescription = "Settings",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun CourseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.google), // Replace with your course image
                contentDescription = "Course Image",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Children drawing Course", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Level 3 - Level 4", fontSize = 14.sp, color = Color.Gray)
                Button(
                    onClick = { /* Handle join click */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF386FFF))
                ) {
                    Text(text = "JOIN", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun LessonCard(lessonTitle: String, time: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes), // Replace with your lesson image
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = lessonTitle, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = time, fontSize = 14.sp, color = Color.Gray)
            }
            IconButton(onClick = { /* Play button action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.google), // Replace with your play icon
                    contentDescription = "Play",
                    tint = Color(0xFFFFC107)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val items = listOf("Home", "Courses", "Profile")
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        0 -> Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Home"
                        ) // Replace with home icon
                        1 -> Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Courses"
                        ) // Replace with course icon
                        2 -> Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Profile"
                        ) // Replace with profile icon
                    }
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    scope.launch { selectedItem = index }
                }
            )
        }
    }
}




data class NavigationItem(
    val title: String,
    @DrawableRes val icon: Int
)

// Define navigation items
object NavigationItems {
    val Home = NavigationItem("Home", R.drawable.ic_home) // Replace with your icon resource
    val Music = NavigationItem("Music", R.drawable.ic_music) // Replace with your icon resource
    val Movies = NavigationItem("Movies", R.drawable.ic_movies) // Replace with your icon resource
    val Books = NavigationItem("Books", R.drawable.ic_books) // Replace with your icon resource
    val Profile = NavigationItem("Profile", R.drawable.ic_profile) // Replace with your icon resource
}


@Composable
fun BottomNavigationBar(onItemSelected: (NavigationItem) -> Unit) {
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Music,
        NavigationItems.Movies,
        NavigationItems.Books,
        NavigationItems.Profile
    )
    var selectedItem by remember { mutableStateOf(items[0]) }

    BottomNavigation(
        backgroundColor = Color(0xFF386FFF),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon),
                            contentDescription = item.title,
//                            modifier = Modifier.size(50.dp)
                )},
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = selectedItem == item,
                onClick = {
                    selectedItem = item
                    onItemSelected(item) // Notify parent of the selection
                }
            )
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar { selectedItem ->
                when (selectedItem) {
                    NavigationItems.Home -> navController.navigate("home")
                    NavigationItems.Music -> navController.navigate("music")
                    NavigationItems.Movies -> navController.navigate("movies")
                    NavigationItems.Books -> navController.navigate("books")
                    NavigationItems.Profile -> navController.navigate("profile")
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen() }
            composable("music") { MusicScreen() }
            composable("movies") { MoviesScreen() }
            composable("books") { BooksScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen", fontSize = 24.sp)
    }
}

@Composable
fun MusicScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Music Screen", fontSize = 24.sp)
    }
}

@Composable
fun MoviesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Movies Screen", fontSize = 24.sp)
    }
}

@Composable
fun BooksScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Books Screen", fontSize = 24.sp)
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen", fontSize = 24.sp)
    }
}

@Preview
@Composable
fun reviewBottomBar()
{
    MainScreen()
}
