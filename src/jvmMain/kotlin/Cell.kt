class Cell(
    val coordinates: Coordinate,
    val type: CellType,
)

class Coordinate(
    val x: Int,
    val y: Int
)
enum class CellType {
    Land, Water, Rock
}