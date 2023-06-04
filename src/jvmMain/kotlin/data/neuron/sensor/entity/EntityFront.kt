package data.neuron.sensor.entity

import data.Cell
import data.Coordinates
import data.Direction
import data.neuron.LogicalNeuron
import data.neuron.NeuronCategory

class EntityFront(
    override var value: Boolean = false,
    id: String = "Ef",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Entity)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(
        coordinates: Coordinates,
        direction: Direction,
        worldSize: Int,
        world:  HashMap<Int, HashMap<Int, Cell>>?
    ): Boolean {
        value = when (direction) {
            coordinates.getFront().any { it.hasEntity }
        }
        return value
    }
}