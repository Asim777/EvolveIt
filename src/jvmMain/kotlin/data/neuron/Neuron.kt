package data.neuron

import data.Cell
import data.Coordinates
import data.Direction
import data.NumberOfNeurons
import data.neuron.sensor.end_of_world.EndOfWorldBehind
import data.neuron.sensor.end_of_world.EndOfWorldFront
import data.neuron.sensor.end_of_world.EndOfWorldLeft
import data.neuron.sensor.end_of_world.EndOfWorldRight
import java.lang.IllegalArgumentException


sealed interface Neuron {
    val id: String
    val category: NeuronCategory

    fun evaluate(
        coordinates: Coordinates,
        direction: Direction,
        worldSize: Int,
        world: HashMap<Int, HashMap<Int, Cell>>? = null
    ): Boolean
}

sealed class NeuronCategory {
    data class Sensor(val subCategory: SensorSubcategory) : NeuronCategory()
    data class Inner(val subCategory: InnerSubcategory) : NeuronCategory()
    data class Sink(val subCategory: SinkSubCategory) : NeuronCategory()

    enum class SensorSubcategory {
        EndOfWorld, Entity, Food, EntityDensity, Distance, GeneticSimilarity
    }

    enum class InnerSubcategory {
        Logical, NumericalBasic, NumericalAdvanced
    }

    enum class SinkSubCategory {
        MovementBasic, MovementAdvanced, Eat, Mate
    }
}

abstract class LogicalNeuron(
    open var value: Boolean,
    override val id: String,
    override val category: NeuronCategory,
) : Neuron

abstract class NumericalNeuron(
    open var value: Float,
    override val id: String,
    override val category: NeuronCategory,
) : Neuron

// SENSOR

// Entity sensory logical neurons

class EntityLeft(
    override var value: Boolean = false,
    id: String = "El",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Entity)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class EntityRight(
    override var value: Boolean = false,
    id: String = "Er",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Entity)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class EntityBehind(
    override var value: Boolean = false,
    id: String = "Eb",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Entity)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Food sensory logical neurons
class FoodAhead(
    override var value: Boolean = false,
    id: String = "Fa",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class FoodFront(
    override var value: Boolean = false,
    id: String = "Ff",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class FoodLeft(
    override var value: Boolean = false,
    id: String = "Fl",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class FoodRight(
    override var value: Boolean = false,
    id: String = "Fr",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class FoodBehind(
    override var value: Boolean = false,
    id: String = "Fb",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class FoodNeighborhood(
    override var value: Boolean = false,
    id: String = "Fl",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class FoodSameLocation(
    override var value: Boolean = false,
    id: String = "Fl",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Food)
) : LogicalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Entity density sensory numerical neurons
class EntityDensityAhead(
    override var value: Float = 0.0f,
    id: String = "EDa",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.EntityDensity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class EntityDensityFront(
    override var value: Float = 0.0f,
    id: String = "EDf",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.EntityDensity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class EntityDensityNeighborhood(
    override var value: Float = 0.0f,
    id: String = "EDn",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.EntityDensity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Distance sensory numerical neurons
class DistanceObject(
    override var value: Float = 0.0f,
    id: String = "DO",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Distance)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class DistanceEndOfWorld(
    override var value: Float = 0.0f,
    id: String = "DEoW",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Distance)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class DistanceEntity(
    override var value: Float = 0.0f,
    id: String = "DE",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Distance)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class DistanceFood(
    override var value: Float = 0.0f,
    id: String = "DF",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.Distance)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Genetic similarity sensory numerical neurons
class GeneticSimilarityAhead(
    override var value: Float = 0.0f,
    id: String = "GSa",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.GeneticSimilarity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class GeneticSimilarityFront(
    override var value: Float = 0.0f,
    id: String = "GSf",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.GeneticSimilarity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class GeneticSimilarityLeft(
    override var value: Float = 0.0f,
    id: String = "GSl",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.GeneticSimilarity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class GeneticSimilarityRight(
    override var value: Float = 0.0f,
    id: String = "GSr",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.GeneticSimilarity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class GeneticSimilarityBehind(
    override var value: Float = 0.0f,
    id: String = "GSb",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.GeneticSimilarity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class GeneticSimilarityNeighborhood(
    override var value: Float = 0.0f,
    id: String = "GSn",
    category: NeuronCategory = NeuronCategory.Sensor(subCategory = NeuronCategory.SensorSubcategory.GeneticSimilarity)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// INNER
// Logical inner neurons
class Not(
    override var value: Float = 0.0f,
    id: String = "!",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.Logical)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class And(
    override var value: Float = 0.0f,
    id: String = "&",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.Logical)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class Or(
    override var value: Float = 0.0f,
    id: String = "|",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.Logical)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class Xor(
    override var value: Float = 0.0f,
    id: String = "^",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.Logical)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Numerical inner neurons
class Less05(
    override var value: Float = 0.0f,
    id: String = "<0.5",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalBasic)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class More05(
    override var value: Float = 0.0f,
    id: String = ">0.5",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalBasic)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class Less025(
    override var value: Float = 0.0f,
    id: String = "<0.25",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class More025(
    override var value: Float = 0.0f,
    id: String = ">0.25",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class Less075(
    override var value: Float = 0.0f,
    id: String = "<0.75",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class More075(
    override var value: Float = 0.0f,
    id: String = ">0.75",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class More09(
    override var value: Float = 0.0f,
    id: String = ">0.9",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class Less01(
    override var value: Float = 0.0f,
    id: String = "<0.1",
    category: NeuronCategory = NeuronCategory.Inner(subCategory = NeuronCategory.InnerSubcategory.NumericalAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// SINK
// Movement sink neurons
class MoveForward(
    override var value: Float = 0.0f,
    id: String = "Mf",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementBasic)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class MoveRight(
    override var value: Float = 0.0f,
    id: String = "Mr",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class MoveLeft(
    override var value: Float = 0.0f,
    id: String = "Ml",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementBasic)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class MoveBack(
    override var value: Float = 0.0f,
    id: String = "Mb",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class MoveRandomly(
    override var value: Float = 0.0f,
    id: String = "Mran",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Turn sink neurons
class TurnRight(
    override var value: Float = 0.0f,
    id: String = "Tr",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementBasic)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class TurnLeft(
    override var value: Float = 0.0f,
    id: String = "Tl",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementAdvanced)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class TurnBack(
    override var value: Float = 0.0f,
    id: String = "Tb",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.MovementBasic)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

// Other sink neurons
class Eat(
    override var value: Float = 0.0f,
    id: String = "E",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.Eat)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

class Mate(
    override var value: Float = 0.0f,
    id: String = "M",
    category: NeuronCategory = NeuronCategory.Sink(subCategory = NeuronCategory.SinkSubCategory.Mate)
) : NumericalNeuron(value, id, category) {
    override fun evaluate(coordinates: Coordinates, direction: Direction, worldSize: Int): Boolean {
        TODO()
    }
}

fun getNeurons(numberOfNeurons: Int): List<Neuron> =
    when (numberOfNeurons) {
        9 -> listOf(
            EndOfWorldFront(),
            EntityFront(),
            FoodAhead(),
            FoodSameLocation(),
            Eat(),
            Mate(),
            MoveForward(),
            TurnRight(),
            TurnLeft()
        )

        17 -> getNeurons(9) + listOf(
            DistanceObject(),
            FoodFront(),
            FoodNeighborhood(),
            EntityDensityAhead(),
            And(),
            Or(),
            Less05(),
            More05()
        )

        22 -> getNeurons(17) + listOf(
            DistanceFood(),
            EntityDensityFront(),
            GeneticSimilarityAhead(),
            Not(),
            MoveRandomly()
        )

        29 -> getNeurons(22) + listOf(
            EntityDensityNeighborhood(),
            GeneticSimilarityFront(),
            Xor(),
            More025(),
            More075(),
            MoveBack(),
            TurnBack()
        )

        37 -> getNeurons(29) + listOf(
            DistanceEndOfWorld(),
            DistanceEntity(),
            FoodLeft(),
            FoodRight(),
            FoodBehind(),
            GeneticSimilarityNeighborhood(),
            Less025(),
            Less075()
        )

        44 -> getNeurons(37) + listOf(
            EntityLeft(),
            EntityRight(),
            EntityBehind(),
            More09(),
            Less01(),
            MoveRight(),
            MoveLeft()
        )

        50-> getNeurons(44) + listOf(
            EndOfWorldLeft(),
            EndOfWorldRight(),
            EndOfWorldBehind(),
            GeneticSimilarityLeft(),
            GeneticSimilarityRight(),
            GeneticSimilarityBehind(),
        )

        else -> {
            throw IllegalArgumentException("Wrong number of neurons inputted")
        }
    }

fun getNeuronDistributionByCategory(numberOfNeurons: Int): NumberOfNeurons =
    getNeurons(numberOfNeurons)
        .groupBy { it.category.javaClass }
        .map { it.key.canonicalName to it.value.size }
        .run {
            NumberOfNeurons(
                total = numberOfNeurons,
                sensorNeurons = find { it.first == NeuronCategory.Sensor::class.java.canonicalName }?.second ?: 0,
                innerNeurons = find { it.first == NeuronCategory.Inner::class.java.canonicalName }?.second ?: 0,
                sinkNeurons = find { it.first == NeuronCategory.Sink::class.java.canonicalName }?.second ?: 0,
            )
        }
