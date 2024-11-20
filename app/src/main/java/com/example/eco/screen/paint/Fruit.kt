enum class LevelStatus {
    COMPLETED, ACTIVE, LOCKED
}

data class Fruit(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val status: LevelStatus // Status for visual state
)
