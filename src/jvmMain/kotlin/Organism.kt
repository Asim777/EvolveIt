import androidx.compose.ui.graphics.Color

class Organism(
    val id: Int,
    val genes: Array<Array<Int>>,
    val color: Color,
    var cellNumber: Int,
    var age: Short,
    var energy: Byte,
    var hunger: Byte,
    var thirst: Byte,
    var hornyness: Byte,
    var sleepiness: Byte
) {
}