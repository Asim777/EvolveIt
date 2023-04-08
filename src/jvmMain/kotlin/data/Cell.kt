package data

class Cell(
    val id: Int,
    val coordinates: Coordinates,
    var hasEntity: Boolean,
    var hasFood: Boolean,
    // val type: CellType
)

class Coordinates(
    val x: Int,
    val y: Int
)

/* TODO: Remove if not used
// In the future we want to implement landscape that the entities will interact with, like swimming in water and
 climbing on rocks
enum class CellType {
    Land, Water, Rock
}*/
