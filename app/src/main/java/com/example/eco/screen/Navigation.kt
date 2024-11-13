package com.example.eco.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eco.R
import com.example.eco.screen.MainScreen

@Composable
fun LoginSignupApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginUIScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("main") { MainScreen() }
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
        backgroundColor = Color.White,
        modifier = Modifier.height(56.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(40.dp),
                    )

                },
                selected = selectedItem == item,
                alwaysShowLabel = false,
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                }
            )
        }
    }
}
