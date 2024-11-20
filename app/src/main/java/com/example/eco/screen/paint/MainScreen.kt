package com.example.eco.screen.paint

import Fruit
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun MainScreen(
////    viewModel: FruitViewModel,
////    onFruitSelected: (Int) -> Unit,
////    onCompleteRound: () -> Unit
////) {
////    val fruits = viewModel.fruits.collectAsState().value
////
////    Scaffold(
////        topBar = {
////            TopAppBar(title = { Text("Bé tập làm họa sĩ") })
////        },
////        content = { paddingValues ->
////            Column(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .padding(paddingValues),
////                verticalArrangement = Arrangement.SpaceBetween
////            ) {
////                // Display fruit grid
////                LazyVerticalGrid(
////                    columns = GridCells.Fixed(2),
////                    modifier = Modifier.weight(1f),
////                    contentPadding = PaddingValues(8.dp)
////                ) {
////                    items(fruits.size) { index ->
////                        val fruit = fruits[index]
////                        FruitItem(fruit = fruit) {
////                            onFruitSelected(fruit.id)
////                        }
////                    }
////                }
////
////                // Check if all fruits are completed
////                val allCompleted = fruits.all { it.status == LevelStatus.COMPLETED }
////
////                // "Complete Round" button
////                Button(
////                    onClick = onCompleteRound,
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(16.dp),
////                    enabled = allCompleted // Ensures this is a Boolean
////                ) {
////                    Text("Hoàn thành Vòng 1")
////                }
////            }
////        }
////    )
////}
//@Composable
//fun FruitItem(fruit: Fruit, onClick: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .padding(8.dp)
//            .clickable(onClick = onClick)
//            .fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = fruit.imageRes),
//            contentDescription = fruit.name,
//            modifier = Modifier.size(100.dp)
//        )
//        Text(
//            text = fruit.name,
//            style = MaterialTheme.typography.bodyMedium,
//            modifier = Modifier.padding(top = 8.dp)
//        )
//    }
//}