package com.example.eco.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
