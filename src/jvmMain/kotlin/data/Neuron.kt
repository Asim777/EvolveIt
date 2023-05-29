package data

import java.lang.IllegalArgumentException


sealed class Neuron(
    val id: String,
    val category: NeuronCategory,
    val dataType: NeuronDataType
) {
    // SENSOR
    // End of the world sensory logical neurons
    class EndOfWorldAhead(
        val value: Boolean = false,
        id: String = "EoWa",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EndOfWorldFront(
        val value: Boolean = false,
        id: String = "EoWf",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EndOfWorldLeft(
        val value: Boolean = false,
        id: String = "EoWl",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EndOfWorldRight(
        val value: Boolean = false,
        id: String = "EoWr",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EndOfWorldBehind(
        val value: Boolean = false,
        id: String = "EoWb",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    // Entity sensory logical neurons
    class EntityFront(
        val value: Boolean = false,
        id: String = "Ef",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EntityLeft(
        val value: Boolean = false,
        id: String = "El",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EntityRight(
        val value: Boolean = false,
        id: String = "Er",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class EntityBehind(
        val value: Boolean = false,
        id: String = "Eb",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    // Food sensory logical neurons
    class FoodAhead(
        val value: Boolean = false,
        id: String = "Fa",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class FoodFront(
        val value: Boolean = false,
        id: String = "Ff",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class FoodLeft(
        val value: Boolean = false,
        id: String = "Fl",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class FoodRight(
        val value: Boolean = false,
        id: String = "Fr",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class FoodBehind(
        val value: Boolean = false,
        id: String = "Fb",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class FoodNeighborhood(
        val value: Boolean = false,
        id: String = "Fl",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class FoodSameLocation(
        val value: Boolean = false,
        id: String = "Fl",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    // Entity density sensory numerical neurons
    class EntityDensityAhead(
        val value: Float = 0.0f,
        id: String = "EDa",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EntityDensity),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class EntityDensityFront(
        val value: Float = 0.0f,
        id: String = "EDf",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EntityDensity),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class EntityDensityNeighborhood(
        val value: Float = 0.0f,
        id: String = "EDn",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.EntityDensity),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    // Distance sensory numerical neurons
    class DistanceObject(
        val value: Float = 0.0f,
        id: String = "DO",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class DistanceEndOfWorld(
        val value: Float = 0.0f,
        id: String = "DEoW",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class DistanceEntity(
        val value: Float = 0.0f,
        id: String = "DE",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class DistanceFood(
        val value: Float = 0.0f,
        id: String = "DF",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    // Genetic similarity sensory numerical neurons
    class GeneticSimilarityAhead(
        val value: Float = 0.0f,
        id: String = "GSa",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class GeneticSimilarityFront(
        val value: Float = 0.0f,
        id: String = "GSf",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class GeneticSimilarityLeft(
        val value: Float = 0.0f,
        id: String = "GSl",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class GeneticSimilarityRight(
        val value: Float = 0.0f,
        id: String = "GSr",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class GeneticSimilarityBehind(
        val value: Float = 0.0f,
        id: String = "GSb",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class GeneticSimilarityNeighborhood(
        val value: Float = 0.0f,
        id: String = "GSn",
        category: NeuronCategory = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    // INNER
    // Logical inner neurons
    class Not(
        val value: Float = 0.0f,
        id: String = "!",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class And(
        val value: Float = 0.0f,
        id: String = "&",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class Or(
        val value: Float = 0.0f,
        id: String = "|",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    class Xor(
        val value: Float = 0.0f,
        id: String = "^",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType: NeuronDataType = NeuronDataType.Logical
    ) : Neuron(id, category, dataType)

    // Numerical inner neurons
    class Less05(
        val value: Float = 0.0f,
        id: String = "<0.5",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalBasic),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class More05(
        val value: Float = 0.0f,
        id: String = ">0.5",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalBasic),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class Less025(
        val value: Float = 0.0f,
        id: String = "<0.25",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class More025(
        val value: Float = 0.0f,
        id: String = ">0.25",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class Less075(
        val value: Float = 0.0f,
        id: String = "<0.75",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class More075(
        val value: Float = 0.0f,
        id: String = ">0.75",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class More09(
        val value: Float = 0.0f,
        id: String = ">0.9",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    class Less01(
        val value: Float = 0.0f,
        id: String = "<0.1",
        category: NeuronCategory = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType: NeuronDataType = NeuronDataType.Numerical
    ) : Neuron(id, category, dataType)

    // SINK
    // Movement sink neurons
    class MoveNorth(
        id: String = "Mn",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveNorthEast(
        id: String = "Mne",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveEast(
        id: String = "Me",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveSouthEast(
        id: String = "Mse",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveSouth(
        id: String = "Ms",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveSouthWest(
        id: String = "Msw",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveWest(
        id: String = "Mw",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveNorthWest(
        id: String = "Mnw",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class MoveRandomly(
        id: String = "Mr",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class DontMove(
        id: String = "DM",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    // Other sink neurons
    class Eat(
        id: String = "E",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.Eat),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    class Mate(
        id: String = "M",
        category: NeuronCategory = NeuronCategory.Sink(subCategory = SinkSubCategory.Mate),
        dataType: NeuronDataType = NeuronDataType.None
    ) : Neuron(id, category, dataType)

    fun evaluate(coordinates: Coordinates, direction: Direction): Boolean =
        when (dataType) {
            NeuronDataType.Logical -> evaluateLogical(coordinates, direction)
            NeuronDataType.Numerical -> TODO()
            NeuronDataType.None -> TODO()
        }

    private fun evaluateLogical(coordinates: Coordinates, direction: Direction): Boolean =
        when (this) {
            is EndOfWorldAhead -> {
                when (direction) {
                    Direction.North,
                    Direction.NorthEast -> {
                        coordinates.y - 5 < 0
                    }

                    Direction.East,
                    Direction.SouthEast -> TODO()

                    Direction.South,
                    Direction.SouthWest -> TODO()

                    Direction.West,
                    Direction.NorthWest -> TODO()
                }
            }

            is EndOfWorldFront -> TODO()
            is EndOfWorldLeft -> TODO()
            is EndOfWorldRight -> TODO()
            is EndOfWorldBehind -> TODO()
            is EntityFront -> TODO()
            is EntityLeft -> TODO()
            is EntityRight -> TODO()
            is EntityBehind -> TODO()
            is FoodAhead -> TODO()
            is FoodFront -> TODO()
            is FoodLeft -> TODO()
            is FoodRight -> TODO()
            is FoodBehind -> TODO()
            is FoodNeighborhood -> TODO()
            is FoodSameLocation -> TODO()
            else -> false
        }
}

sealed class NeuronCategory {
    data class Sensor(val subCategory: SensorSubcategory) : NeuronCategory()
    data class Inner(val subCategory: InnerSubcategory) : NeuronCategory()
    data class Sink(val subCategory: SinkSubCategory) : NeuronCategory()
}

enum class SensorSubcategory {
    EndOfWorld, Entity, Food, EntityDensity, Distance, GeneticSimilarity, Any
}

enum class InnerSubcategory {
    Logical, NumericalBasic, NumericalAdvanced, Any
}

enum class SinkSubCategory {
    MovementBasic, MovementAdvanced, Eat, Mate, Any
}

enum class NeuronDataType {
    Logical, Numerical, None
}

fun getNeurons(numberOfNeurons: Int): List<Neuron> =
    when (numberOfNeurons) {
        11 -> listOf(
            Neuron.EndOfWorldAhead(),
            Neuron.EntityFront(),
            Neuron.FoodAhead(),
            Neuron.FoodSameLocation(),
            Neuron.MoveNorth(),
            Neuron.MoveEast(),
            Neuron.MoveSouth(),
            Neuron.MoveWest(),
            Neuron.DontMove(),
            Neuron.Eat(),
            Neuron.Mate()
        )

        20 -> getNeurons(11) + listOf(
            Neuron.DistanceObject(),
            Neuron.EndOfWorldFront(),
            Neuron.FoodFront(),
            Neuron.FoodNeighborhood(),
            Neuron.EntityDensityAhead(),
            Neuron.And(),
            Neuron.Or(),
            Neuron.Less05(),
            Neuron.More05()
        )

        25 -> getNeurons(20) + listOf(
            Neuron.DistanceFood(),
            Neuron.EntityDensityFront(),
            Neuron.GeneticSimilarityAhead(),
            Neuron.Not(),
            Neuron.MoveRandomly()
        )

        34 -> getNeurons(25) + listOf(
            Neuron.EntityDensityNeighborhood(),
            Neuron.GeneticSimilarityFront(),
            Neuron.Xor(),
            Neuron.More025(),
            Neuron.More075(),
            Neuron.MoveNorthEast(),
            Neuron.MoveSouthEast(),
            Neuron.MoveSouthWest(),
            Neuron.MoveNorthWest()
        )

        42 -> getNeurons(34) + listOf(
            Neuron.DistanceEndOfWorld(),
            Neuron.DistanceEntity(),
            Neuron.FoodLeft(),
            Neuron.FoodRight(),
            Neuron.FoodBehind(),
            Neuron.GeneticSimilarityNeighborhood(),
            Neuron.Less025(),
            Neuron.Less075()
        )

        47 -> getNeurons(42) + listOf(
            Neuron.EntityLeft(),
            Neuron.EntityRight(),
            Neuron.EntityBehind(),
            Neuron.More09(),
            Neuron.Less01()
        )

        53 -> getNeurons(47) + listOf(
            Neuron.EndOfWorldLeft(),
            Neuron.EndOfWorldRight(),
            Neuron.EndOfWorldBehind(),
            Neuron.GeneticSimilarityLeft(),
            Neuron.GeneticSimilarityRight(),
            Neuron.GeneticSimilarityBehind(),
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