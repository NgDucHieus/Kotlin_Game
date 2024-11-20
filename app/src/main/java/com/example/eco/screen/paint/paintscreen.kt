package com.example.eco.screen.paint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController




@Composable
fun PaintScreen() {
    val navController = rememberNavController()
    val viewModel: FruitViewModel = viewModel()

    NavHost(navController = navController, startDestination = "fruitList") {
        composable("fruitList") {
            FruitLevelScreen(
                viewModel = viewModel,
                onFruitClick = { fruitId ->
                    navController.navigate("fruitDetail/$fruitId")
                }
            )
        }
        composable("fruitDetail/{fruitId}") { backStackEntry ->
            val fruitId = backStackEntry.arguments?.getString("fruitId")?.toInt() ?: 0
            val fruit = viewModel.fruits.value.find { it.id == fruitId }
            fruit?.let {
                DetailScreen(
                    fruit = it,
                    onBack = { navController.popBackStack() },
                    onComplete = {
                        viewModel.markFruitCompleted(it.id) // Mark fruit as completed
                        navController.popBackStack() // Return to the fruit list
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun Prevasd()
{
    PaintScreen()
}
