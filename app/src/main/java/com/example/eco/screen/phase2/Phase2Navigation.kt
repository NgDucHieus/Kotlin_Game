package com.example.eco.screen.phase2


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Phase2Navigation(onNavigateNextPhase: () -> Unit) {
    val navController = rememberNavController()
    val context = LocalContext.current // Retrieve the current context

    NavHost(navController = navController, startDestination = "phase2_main") {
        composable("phase2_main") {
            ECountingScreen(context = context) {
                navController.navigate("phase2_next")
            }
        }
        composable("phase2_next") {
            // Placeholder for the next screen
            onNavigateNextPhase()
        }
    }
}