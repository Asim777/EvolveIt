package data

class WorldParams(
    // W
    val worldSize: Int,
    // E
    val initialPopulation: Int,
    // G
    val genomeLength: Short,
    // F
    val foodAvailability: Float,
    val mutationRate: Float,
    // N_sn
    val numberOfSensorNeurons: Short,
    // N_sn
    val numberOfInnerNeurons: Short,
    // N_sk
    val numberOfSinkNeurons: Short,
)