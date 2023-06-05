package data.neuron.sensor.entity

import data.*
import data.neuron.LogicalNeuron
import data.neuron.NeuronCategory

class EntityFront(
    override var value: Boolean = false,
    id: String = "Ef",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Entity)
) : LogicalNeuron(value, id, category) {
    // TODO: Write tests for method
    override fun evaluate(entity: Entity, worldSize: Int): Boolean {
        value = with(entity) {
            fieldOfView.getFront().any { it?.hasEntity == true }
        }
        return value
    }
}