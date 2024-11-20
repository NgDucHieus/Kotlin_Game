//package com.example.eco.screen.paint
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    val viewModel: FruitViewModel = viewModel()
//
//    NavHost(navController = navController, startDestination = "main") {
//        composable("main") {
//            MainScreen(
//                viewModel = viewModel,
//                onFruitSelected = { fruitId ->
//                    navController.navigate("detail/$fruitId")
//                },
//                onCompleteRound = {
//                    // Handle round completion
//                }
//            )
//        }
//        composable("detail/{fruitId}") { backStackEntry ->
//            val fruitId = backStackEntry.arguments?.getString("fruitId")?.toInt() ?: 0
//            val fruit = viewModel.fruits.collectAsState().value.find { it.id == fruitId }
//            fruit?.let {
//                DetailScreen(
//                    fruit = it,
//                    onCompleted = {
//                        viewModel.markFruitCompleted(it.id)
//                        navController.popBackStack()
//                    },
//                    onBack = { navController.popBackStack() }
//                )
//            }
//        }
//    }
//}
