package data.neuron.sensor.end_of_world

import data.*
import data.neuron.LogicalNeuron
import data.neuron.NeuronCategory

class EndOfWorldLeft(
    override var value: Boolean = false,
    id: String = "EoWl",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.EndOfWorld)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(entity: Entity, worldSize: Int): Boolean {
        value = with(entity) {
            when (direction) {
                Direction.North -> coordinates.x - 5 < 0
                Direction.East -> coordinates.y - 5 < 0
                Direction.South -> coordinates.x + 5 < worldSize
                Direction.West ->  coordinates.y + 5 < worldSize
            }
        }
        return value
    }
}