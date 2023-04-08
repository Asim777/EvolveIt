import androidx.compose.ui.graphics.Color
import data.*
import kotlin.math.pow

class Simulation(private val worldParams: WorldParams) {

    private var randomNumberCursorPosition = 0
    private val randomBytesString by lazy {
        object {}.javaClass.getResource("random/1")?.readText()
    }
    private val world = hashMapOf<Int, HashMap<Int, Cell>>()
    private val genePool = arrayOf<Array<Gene>>()
    private val entities = arrayOf<Entity>()

    fun setup() {
        // Create the World
        for (column in 0 until worldParams.worldSize) {
            world[column] = hashMapOf()
            for (row in 0 until worldParams.worldSize) {
                world[column]?.set(
                    key = row,
                    value = Cell(
                        id = column + row,
                        coordinates = Coordinates(column, row),
                        hasEntity = false,
                        hasFood = false
                    )
                ) ?: throw IllegalStateException("Cell column is null")
            }
        }

        createInitialGenePool()
        createEntities()

    }

    private fun createInitialGenePool() {
        // Create Neurons
        val sensorNeurons = getNeurons(NeuronCategory.Sensor, worldParams.numberOfSensorNeurons.toInt())
        val innerNeurons = getNeurons(NeuronCategory.Inner, worldParams.numberOfInnerNeurons.toInt())
        val sinkNeurons = getNeurons(NeuronCategory.Sink, worldParams.numberOfSinkNeurons.toInt())

        // Create Gene pool
        for (i in 0 until worldParams.initialPopulation) {
            val genome = arrayOf<Gene>()
            for (j in 0 until worldParams.genomeLength) {
                // Create a gene and assign to the genome
                genome[j] = Gene(
                    input = sensorNeurons.plus(innerNeurons).run {
                        get(getRandomNumber(size))
                    },
                    output = innerNeurons.plus(sinkNeurons).run {
                        get(getRandomNumber(size))
                    },
                    weight = getRandomFloat(2)
                )
            }

            // Assign the genome to the Gene pool
            genePool[i] = genome
        }
    }

    private fun createEntities() {
        for (i in 0 until worldParams.initialPopulation) {
            val genome = genePool[i]
            Entity(
                id = i,
                genome = genome,
                color = genome.generateColor(),
                coordinates = getRandomCoordinate(),
                age = 0,
                energy = 100,
                hunger = 0,
                hornyness = 0
            )
        }
    }

    private fun getNeurons(category: NeuronCategory, numberOfNeurons: Int) =
        NeuronType.values()
            .filter { it.category == category }
            .mapIndexed { index, value ->
                Neuron(
                    id = index.toShort(),
                    type = value
                )
            }
            .take(numberOfNeurons)

    /**
     * Return
     */
    private fun getRandomCoordinate(): Coordinates {
        var coord: Coordinates
        do {
            coord = Coordinates(
                x = getRandomNumber(worldParams.worldSize),
                y = getRandomNumber(worldParams.worldSize)
            )
        } while (world[coord.x]?.get(coord.y)?.hasEntity?.not() ?: throw IllegalStateException("Cell is null"))
        return coord
    }

    /**
     * Generate a random number in range of [0, max]
     *
     * @param max number to apply as an exclusive upper limit to the range of random numbers
     * @return generated Int value
     */
    private fun getRandomNumber(max: Int): Int {
        // Calculate a minimum number of bits needed to represent the maximum
        val minNumberOfBits = Integer.toBinaryString(max).length
        // Get minNumberOfBits bits from a file starting at randomNumberCursorPosition
        val randomBits =
            randomBytesString?.substring(randomNumberCursorPosition, randomNumberCursorPosition + minNumberOfBits)
        // Update cursor position so that the next time this method is called, it fetches unused bits from the file
        randomNumberCursorPosition += minNumberOfBits
        // Convert selected random bits to an integer and return it
        randomBits?.let {
            runCatching {
                return randomBits.toInt(2)
            }.getOrElse {
                throw IllegalStateException("Failed to convert bits string into integer")
            }
        } ?: throw IllegalStateException("Failed to generate random number. randomBits is null")
    }

    /**
     * Generate a random float number in range of [0.0, 1.0]
     *
     * @param decimalPoints number of decimal points that the generated float number should have
     * @return generated Float value
     */
    private fun getRandomFloat(decimalPoints: Int): Float {
        // Calculate a minimum number of bits needed to represent the float number with given decimal points
        val minNumberOfBits = 10.0.pow(decimalPoints).toInt()
        // Get minNumberOfBits bits from a file starting at randomNumberCursorPosition
        val randomBits =
            randomBytesString?.substring(randomNumberCursorPosition, randomNumberCursorPosition + minNumberOfBits)
        // Update cursor position so that the next time this method is called, it fetches unused bits from the file
        randomNumberCursorPosition += minNumberOfBits
        // Convert selected random bits to an integer and return it
        randomBits?.let {
            runCatching {
                return randomBits.toInt(2).toFloat() / 100
            }.getOrElse {
                throw IllegalStateException("Failed to convert bits string into integer")
            }
        } ?: throw IllegalStateException("Failed to generate random number. randomBits is null")
    }
}

/**
 * Generate a random color based on the genes.
 * We need it to visually represent the diversity and genetic similarity
 *
 * @return generated Color
 */
fun Array<Gene>.generateColor(): Color {
    val genomeHashCode = this.contentHashCode()
    return Color(genomeHashCode and 0xFFFFFF)
}