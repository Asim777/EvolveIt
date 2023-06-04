package data

import androidx.compose.ui.graphics.Color
import data.neuron.LogicalNeuron
import data.neuron.NumericalNeuron

class Entity(
    val id: Int,
    val genome: Array<Gene>,
    val color: Color,
    var coordinates: Coordinates,
    var direction: Direction,
    var fieldOfView: FieldOfView,
    var age: Int,
    var energy: Int,
    var hunger: Int,
    //var thirst: Int,
    var sexualDrive: Int,
    //var sleepiness: Int
)

data class FieldOfView(
    var f1: Cell?,
    var fr: Cell?,
    var r: Cell?,
    var br: Cell?,
    var b: Cell?,
    var bl: Cell?,
    var l: Cell?,
    var fl: Cell?,
    var f2: Cell?,
    var f1r2: Cell?,
    var f1l2: Cell?,
    var fr2: Cell?,
    var fl2: Cell?,
    var f3: Cell?,
    var f2r3: Cell?,
    var f2l3: Cell?,
    var f1r3: Cell?,
    var f1l3: Cell?,
    var fr3: Cell?,
    var fl3: Cell?,
    var f4: Cell?,
    var f3r4: Cell?,
    var f3l4: Cell?,
    var f2r4: Cell?,
    var f2l4: Cell?,
    var f1r4: Cell?,
    var f1l4: Cell?,
    var fr4: Cell?,
    var fl4: Cell?
)

enum class Direction {
    North,
    East,
    South,
    West
}

fun Entity.evaluateInputData(worldSize: Int) {
    // Do not evaluate useless genes that can't possibly result in action
    genome.forEach { gene ->
        with(gene) {
            if (
                (input is LogicalNeuron && output is LogicalNeuron) or
                (input is NumericalNeuron && output is NumericalNeuron)
            ) {
                input.evaluate(coordinates, direction, worldSize)
            }
        }
    }
}

fun Entity.calculateFieldOfView(world: HashMap<Int, HashMap<Int, Cell>>) {

    fun Coordinates.getCell() = world[this.x]?.get(this.y)

    // Get geographic location
    fun Coordinates.getNorth(step: Int) =
        if (y > step - 1) {
            copy(y = y - step).getCell()
        } else null

    fun Coordinates.getNorthEast(step: Int) =
        if (x < world.size - step && y > step - 1) {
            copy(x = x + step, y = y - step).getCell()
        } else null

    fun Coordinates.getNorthWest(step: Int) =
        if (x > step - 1 && y > step - 1) {
            copy(x = x - step, y = y - step).getCell()
        } else null

    fun Coordinates.getEast(step: Int) =
        if (x < world.size - step) {
            copy(x = x + step).getCell()
        } else null

    fun Coordinates.getSouth(step: Int) =
        if (y < world.size - step) {
            copy(y = y + step).getCell()
        } else null

    fun Coordinates.getSouthEast(step: Int) =
        if (x < world.size - step && y < world.size - step) {
            copy(x = x + step, y = y + step).getCell()
        } else null

    fun Coordinates.getSouthWest(step: Int) =
        if (x > step - 1 && y < world.size - step) {
            copy(x = x - step, y = y + step).getCell()
        } else null

    fun Coordinates.getWest(step: Int) =
        if (x > step - 1) {
            copy(x = x - step).getCell()
        } else null


    // Get relative location
    fun Coordinates.getFront(step: Int) =
        when (direction) {
            Direction.North -> getNorth(step)
            Direction.East -> getEast(step)
            Direction.South -> getSouth(step)
            Direction.West -> getWest(step)
        }

    fun Coordinates.getFrontRight(step: Int) =
        when (direction) {
            Direction.North -> getNorthEast(step)
            Direction.East -> getSouthEast(step)
            Direction.South -> getSouthWest(step)
            Direction.West -> getNorthWest(step)
        }

    fun Coordinates.getRight(step: Int) =
        when (direction) {
            Direction.North -> getEast(step)
            Direction.East -> getSouth(step)
            Direction.South -> getWest(step)
            Direction.West -> getNorth(step)
        }

    fun Coordinates.getBackRight(step: Int) =
        when (direction) {
            Direction.North -> getSouthEast(step)
            Direction.East -> getSouthWest(step)
            Direction.South -> getNorthWest(step)
            Direction.West -> getNorthEast(step)
        }

    fun Coordinates.getBack(step: Int) =
        when (direction) {
            Direction.North -> getSouth(step)
            Direction.East -> getWest(step)
            Direction.South -> getNorth(step)
            Direction.West -> getEast(step)
        }

    fun Coordinates.getBackLeft(step: Int) =
        when (direction) {
            Direction.North -> getSouthWest(step)
            Direction.East -> getNorthWest(step)
            Direction.South -> getNorthEast(step)
            Direction.West -> getSouthEast(step)
        }

    fun Coordinates.getLeft(step: Int) =
        when (direction) {
            Direction.North -> getWest(step)
            Direction.East -> getNorth(step)
            Direction.South -> getEast(step)
            Direction.West -> getSouth(step)
        }

    fun Coordinates.getFrontLeft(step: Int) =
        when (direction) {
            Direction.North -> getNorthWest(step)
            Direction.East -> getNorthEast(step)
            Direction.South -> getSouthEast(step)
            Direction.West -> getSouthWest(step)
        }

    with(fieldOfView) {
        f1 = coordinates.getFront(1)
        fr = coordinates.getFrontRight(1)
        r = coordinates.getRight(1)
        br = coordinates.getBackRight(1)
        b = coordinates.getBack(1)
        bl = coordinates.getBackLeft(1)
        l = coordinates.getLeft(1)
        fl = coordinates.getFrontLeft(1)
        f2 = coordinates.getFront(2)
        f1r2 = coordinates.getFront(2)?.coordinates?.getRight(1)
        f1l2 = coordinates.getFront(2)?.coordinates?.getLeft(1)
        fr2 = coordinates.getFrontRight(2)
        fl2 = coordinates.getFrontLeft(2)
        f3 = coordinates.getFront(3)
        f2r3 = coordinates.getFront(3)?.coordinates?.getRight(1)
        f2l3 = coordinates.getFront(3)?.coordinates?.getLeft(1)
        f1r3 = coordinates.getFront(3)?.coordinates?.getRight(2)
        f1l3 = coordinates.getFront(3)?.coordinates?.getLeft(2)
        fr3 = coordinates.getFrontRight(3)
        fl3 = coordinates.getFrontLeft(3)
        f4 = coordinates.getFront(4)
        f3r4 = coordinates.getFront(4)?.coordinates?.getRight(1)
        f3l4 = coordinates.getFront(4)?.coordinates?.getLeft(1)
        f2r4 = coordinates.getFront(4)?.coordinates?.getRight(2)
        f2l4 = coordinates.getFront(4)?.coordinates?.getLeft(2)
        f1r4 = coordinates.getFront(4)?.coordinates?.getRight(3)
        f1l4 = coordinates.getFront(4)?.coordinates?.getLeft(3)
        fr4 = coordinates.getFrontRight(4)
        fl4 = coordinates.getFrontLeft(4)
    }
}