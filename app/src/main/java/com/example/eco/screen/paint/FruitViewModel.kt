package com.example.eco.screen.paint

import Fruit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eco.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FruitViewModel : ViewModel() {
    private val _fruits = MutableStateFlow(
        listOf(
            Fruit(1, "Cam", R.drawable.orange, LevelStatus.COMPLETED),
            Fruit(2, "Táo", R.drawable.apple, LevelStatus.ACTIVE),
            Fruit(3, "Nho", R.drawable.grape, LevelStatus.LOCKED),
            Fruit(4, "Chuối", R.drawable.banana, LevelStatus.LOCKED),
            Fruit(5, "Dưa hấu", R.drawable.watermelon, LevelStatus.LOCKED),
            Fruit(6, "Dâu tây", R.drawable.strawberry, LevelStatus.LOCKED),
            Fruit(7, "Lê", R.drawable.pear, LevelStatus.LOCKED),
            Fruit(8, "Xoài", R.drawable.mango, LevelStatus.LOCKED)
        )
    )
    val fruits: StateFlow<List<Fruit>> = _fruits

    fun markFruitCompleted(fruitId: Int) {
        _fruits.value = _fruits.value.map { fruit ->
            if (fruit.id == fruitId) fruit.copy(status = LevelStatus.COMPLETED)
            else if (fruit.id == fruitId + 1 && fruit.status == LevelStatus.LOCKED) fruit.copy(status = LevelStatus.ACTIVE)
            else fruit
        }
    }
}
