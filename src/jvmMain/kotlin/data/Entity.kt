package data

import androidx.compose.ui.graphics.Color

class Entity(
    val id: Int,
    val genome: Array<Gene>,
    val color: Color,
    var coordinates: Coordinates,
    var direction: Direction,
    var age: Int,
    var energy: Int,
    var hunger: Int,
    //var thirst: Int,
    var hornyness: Int,
    //var sleepiness: Int
)

enum class Direction {
    North,
    NorthEast,
    East,
    SouthEast,
    South,
    SouthWest,
    West,
    NorthWest
}