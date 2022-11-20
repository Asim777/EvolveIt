class Cell(
    val cellId: Int,
    val coordinates: Pair<Int, Int>,
    val type: CellType,
)

enum class CellType {
    Land, Water, Rock
}