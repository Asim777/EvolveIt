package data

class Neuron(
    val id: Short,
    val type: NeuronType
)

enum class NeuronType(val category: NeuronCategory, val dataType: NeuronDataType) {
    // SENSOR
    // End of the world sensory logical neurons
    EndOfWorldAhead(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EndOfWorldFront(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EndOfWorldLeft(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EndOfWorldRight(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EndOfWorldBehind(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),

    // Entity sensory logical neurons
    EntityFront(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EntityLeft(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EntityRight(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    EntityBehind(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),

    // Food sensory logical neurons
    FoodAhead(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    FoodFront(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    FoodLeft(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    FoodRight(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    FoodBehind(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    FoodNeighborhood(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    FoodSameLocation(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),

    // Entity density sensory numerical neurons
    EntityDensityAhead(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),
    EntityDensityFront(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),
    EntityDensityNeighborhood(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),

    // Distance sensory numerical neurons
    DistanceObject(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),
    DistanceEndOfWorld(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),
    DistanceEntity(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),
    DistanceFood(category = NeuronCategory.Sensor, dataType = NeuronDataType.Numerical),

    // Genetic similarity sensory numerical neurons
    GeneticSimilarityAhead(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    GeneticSimilarityFront(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    GeneticSimilarityLeft(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    GeneticSimilarityRight(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    GeneticSimilarityBehind(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),
    GeneticSimilarityNeighborhood(category = NeuronCategory.Sensor, dataType = NeuronDataType.Logical),

    // INNER
    // Logical inner neurons
    Not(category = NeuronCategory.Inner, dataType = NeuronDataType.Logical),
    And(category = NeuronCategory.Inner, dataType = NeuronDataType.Logical),
    Or(category = NeuronCategory.Inner, dataType = NeuronDataType.Logical),
    Xor(category = NeuronCategory.Inner, dataType = NeuronDataType.Logical),

    // Numerical inner neurons
    Less05(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    More05(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    Less025(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    More025(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    Less075(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    More075(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    More09(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),
    Less01(category = NeuronCategory.Inner, dataType = NeuronDataType.Numerical),

    // SINK
    // Movement sink neurons
    MoveNorth(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveNorthEast(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveEast(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveSouthEast(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveSouth(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveSouthWest(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveWest(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveNorthWest(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    MoveRandomly(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    DontMove(category = NeuronCategory.Sink, dataType = NeuronDataType.None),

    // Other sink neurons
    Eat(category = NeuronCategory.Sink, dataType = NeuronDataType.None),
    Mate(category = NeuronCategory.Sink, dataType = NeuronDataType.None)
}

enum class NeuronCategory {
    Sensor,
    Inner,
    Sink
}

enum class NeuronDataType {
    Logical,
    Numerical,
    None
}