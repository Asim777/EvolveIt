package data.neuron.sensor.end_of_world

import data.Cell
import data.Coordinates
import data.Direction
import data.neuron.LogicalNeuron
import data.neuron.NeuronCategory

class EndOfWorldFront(
    override var value: Boolean = false,
    id: String = "EoWf",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.EndOfWorld)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(
        coordinates: Coordinates,
        direction: Direction,
        worldSize: Int,
        world:  HashMap<Int, HashMap<Int, Cell>>?
    ): Boolean {
        value = when (direction) {
            Direction.North -> coordinates.y - 5 < 0
            Direction.East -> coordinates.x + 5 < worldSize
            Direction.South -> coordinates.y + 5 < worldSize
            Direction.West -> coordinates.x - 5 < 0
        }
        return value
    }
}