package com.example.eco.screen.paint

import Fruit
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FruitLevelScreen(viewModel: FruitViewModel, onFruitClick: (Int) -> Unit) {
    val fruits = viewModel.fruits.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(fruits.size) { index ->
            val fruit = fruits[index]
            FruitLevelItem(fruit = fruit, onClick = { onFruitClick(fruit.id) })
        }
    }
}

@Composable
fun FruitLevelItem(fruit: Fruit, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(enabled = fruit.status == LevelStatus.ACTIVE, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circle Background for Status
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = when (fruit.status) {
                        LevelStatus.COMPLETED -> Color(0xFFFFD700) // Gold for completed
                        LevelStatus.ACTIVE -> Color(0xFF81C784) // Green for active
                        LevelStatus.LOCKED -> Color(0xFFBDBDBD) // Gray for locked
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            // Fruit Icon
            Image(
                painter = painterResource(id = fruit.imageRes),
                contentDescription = fruit.name,
                modifier = Modifier.size(48.dp),
                colorFilter = if (fruit.status == LevelStatus.LOCKED) ColorFilter.tint(Color.Gray) else null
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Fruit Name
        Text(
            text = fruit.name,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
