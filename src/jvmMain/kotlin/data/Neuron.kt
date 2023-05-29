package data

import java.lang.IllegalArgumentException


enum class Neuron(val id: String, val category: NeuronCategory, val dataType: NeuronDataType) {
    // SENSOR
    // End of the world sensory logical neurons
    EndOfWorldAhead(
        id = "EoWa",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType = NeuronDataType.Logical
    ),
    EndOfWorldFront(
        id = "EoWf",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType = NeuronDataType.Logical
    ),
    EndOfWorldLeft(
        id = "EoWl",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType = NeuronDataType.Logical
    ),
    EndOfWorldRight(
        id = "EoWr",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType = NeuronDataType.Logical
    ),
    EndOfWorldBehind(
        id = "EoWb",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EndOfWorld),
        dataType = NeuronDataType.Logical
    ),

    // Entity sensory logical neurons
    EntityFront(
        id = "Ef",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType = NeuronDataType.Logical
    ),
    EntityLeft(
        id = "El",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType = NeuronDataType.Logical
    ),
    EntityRight(
        id = "Er",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType = NeuronDataType.Logical
    ),
    EntityBehind(
        id = "Eb",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Entity),
        dataType = NeuronDataType.Logical
    ),

    // Food sensory logical neurons
    FoodAhead(
        id = "Fa",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),
    FoodFront(
        id = "Ff",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),
    FoodLeft(
        id = "Fl", category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),
    FoodRight(
        id = "Fr",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),
    FoodBehind(
        id = "Fb",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),
    FoodNeighborhood(
        id = "Fn",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),
    FoodSameLocation(
        id = "Fsl",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Food),
        dataType = NeuronDataType.Logical
    ),

    // Entity density sensory numerical neurons
    EntityDensityAhead(
        id = "EDa",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EntityDensity),
        dataType = NeuronDataType.Numerical
    ),
    EntityDensityFront(
        id = "EDf",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EntityDensity),
        dataType = NeuronDataType.Numerical
    ),
    EntityDensityNeighborhood(
        id = "EDn",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.EntityDensity),
        dataType = NeuronDataType.Numerical
    ),

    // Distance sensory numerical neurons
    DistanceObject(
        id = "DO",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType = NeuronDataType.Numerical
    ),
    DistanceEndOfWorld(
        id = "DEoW",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType = NeuronDataType.Numerical
    ),
    DistanceEntity(
        id = "DE",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType = NeuronDataType.Numerical
    ),
    DistanceFood(
        id = "DF",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.Distance),
        dataType = NeuronDataType.Numerical
    ),

    // Genetic similarity sensory numerical neurons
    GeneticSimilarityAhead(
        id = "GSa",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType = NeuronDataType.Logical
    ),
    GeneticSimilarityFront(
        id = "GSf",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType = NeuronDataType.Logical
    ),
    GeneticSimilarityLeft(
        id = "GSl",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType = NeuronDataType.Logical
    ),
    GeneticSimilarityRight(
        id = "GSr",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType = NeuronDataType.Logical
    ),
    GeneticSimilarityBehind(
        id = "GSb",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType = NeuronDataType.Logical
    ),
    GeneticSimilarityNeighborhood(
        id = "GSn",
        category = NeuronCategory.Sensor(subCategory = SensorSubcategory.GeneticSimilarity),
        dataType = NeuronDataType.Logical
    ),

    // INNER
    // Logical inner neurons
    Not(
        id = "!",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType = NeuronDataType.Logical
    ),
    And(
        id = "&",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType = NeuronDataType.Logical
    ),
    Or(
        id = "|",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType = NeuronDataType.Logical
    ),
    Xor(
        id = "^",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.Logical),
        dataType = NeuronDataType.Logical

    ),

    // Numerical inner neurons
    Less05(
        id = "<0.5",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalBasic),
        dataType = NeuronDataType.Numerical
    ),
    More05(
        id = ">0.5",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalBasic),
        dataType = NeuronDataType.Numerical
    ),
    Less025(
        id = "<0.25",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType = NeuronDataType.Numerical
    ),
    More025(
        id = ">0.25",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType = NeuronDataType.Numerical
    ),
    Less075(
        id = "<0.75",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType = NeuronDataType.Numerical
    ),
    More075(
        id = ">0.75",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType = NeuronDataType.Numerical
    ),
    More09(
        id = ">0.9",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType = NeuronDataType.Numerical
    ),
    Less01(
        id = "<0.1",
        category = NeuronCategory.Inner(subCategory = InnerSubcategory.NumericalAdvanced),
        dataType = NeuronDataType.Numerical
    ),

    // SINK
    // Movement sink neurons
    MoveNorth(
        id = "Mn",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic), dataType = NeuronDataType.None
    ),
    MoveNorthEast(
        id = "Mne",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced), dataType = NeuronDataType.None
    ),
    MoveEast(
        id = "Me",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic), dataType = NeuronDataType.None
    ),
    MoveSouthEast(
        id = "Mse",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced), dataType = NeuronDataType.None
    ),
    MoveSouth(
        id = "Ms",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic), dataType = NeuronDataType.None
    ),
    MoveSouthWest(
        id = "Msw",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced), dataType = NeuronDataType.None
    ),
    MoveWest(
        id = "Mw",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic), dataType = NeuronDataType.None
    ),
    MoveNorthWest(
        id = "Mnw",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced), dataType = NeuronDataType.None
    ),
    MoveRandomly(
        id = "Mr",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementAdvanced), dataType = NeuronDataType.None
    ),
    DontMove(
        id = "DM",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.MovementBasic), dataType = NeuronDataType.None
    ),

    // Other sink neurons
    Eat(
        id = "E",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.Eat),
        dataType = NeuronDataType.None
    ),
    Mate(
        id = "M",
        category = NeuronCategory.Sink(subCategory = SinkSubCategory.Mate),
        dataType = NeuronDataType.None
    )
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
            Neuron.EndOfWorldAhead,
            Neuron.EntityFront,
            Neuron.FoodAhead,
            Neuron.FoodSameLocation,
            Neuron.MoveNorth,
            Neuron.MoveEast,
            Neuron.MoveSouth,
            Neuron.MoveWest,
            Neuron.DontMove,
            Neuron.Eat,
            Neuron.Mate
        )

        20 -> getNeurons(11) + listOf(
            Neuron.DistanceObject,
            Neuron.EndOfWorldFront,
            Neuron.FoodFront,
            Neuron.FoodNeighborhood,
            Neuron.EntityDensityAhead,
            Neuron.And,
            Neuron.Or,
            Neuron.Less05,
            Neuron.More05
        )

        25 -> getNeurons(20) + listOf(
            Neuron.DistanceFood,
            Neuron.EntityDensityFront,
            Neuron.GeneticSimilarityAhead,
            Neuron.Not,
            Neuron.MoveRandomly
        )

        34 -> getNeurons(25) + listOf(
            Neuron.EntityDensityNeighborhood,
            Neuron.GeneticSimilarityFront,
            Neuron.Xor,
            Neuron.More025,
            Neuron.More075,
            Neuron.MoveNorthEast,
            Neuron.MoveSouthEast,
            Neuron.MoveSouthWest,
            Neuron.MoveNorthWest
        )

        42 -> getNeurons(34) + listOf(
            Neuron.DistanceEndOfWorld,
            Neuron.DistanceEntity,
            Neuron.FoodLeft,
            Neuron.FoodRight,
            Neuron.FoodBehind,
            Neuron.GeneticSimilarityNeighborhood,
            Neuron.Less025,
            Neuron.Less075
        )

        47 -> getNeurons(42) + listOf(
            Neuron.EntityLeft,
            Neuron.EntityRight,
            Neuron.EntityBehind,
            Neuron.More09,
            Neuron.Less01
        )

        53 -> getNeurons(47) + listOf(
            Neuron.EndOfWorldLeft,
            Neuron.EndOfWorldRight,
            Neuron.EndOfWorldBehind,
            Neuron.GeneticSimilarityLeft,
            Neuron.GeneticSimilarityRight,
            Neuron.GeneticSimilarityBehind,
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