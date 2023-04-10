import androidx.compose.ui.graphics.Color
import data.*
import kotlin.math.pow

class Simulation(private val worldParams: WorldParams) {

    private var randomNumberCursorPosition = 0
    private var currentRandomBitsFileNumber = 0
    private var randomBytesString = object {}.javaClass.getResource("random/1")?.readText()
    private val world = hashMapOf<Int, HashMap<Int, Cell>>()
    private val genePool = mutableListOf<Array<Gene>>()
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
        println("Finished")
    }

    private fun createInitialGenePool() {
        // Create Neurons
        val sensorNeurons = getNeurons(NeuronCategory.Sensor, worldParams.numberOfSensorNeurons.toInt())
        val innerNeurons = getNeurons(NeuronCategory.Inner, worldParams.numberOfInnerNeurons.toInt())
        val sinkNeurons = getNeurons(NeuronCategory.Sink, worldParams.numberOfSinkNeurons.toInt())

        // Create Gene pool
        for (i in 0 until worldParams.initialPopulation) {
            val genome = mutableListOf<Gene>()
            for (j in 0 until worldParams.genomeLength) {
                // Create a gene and assign to the genome
                genome.add(
                    j, Gene(
                        input = sensorNeurons.plus(innerNeurons).run {
                            get(getRandomNumber(size))
                        },
                        output = innerNeurons.plus(sinkNeurons).run {
                            get(getRandomNumber(size))
                        },
                        weight = getRandomFloat(2)
                    )
                )
            }

            // Assign the genome to the Gene pool
            genePool.add(i, genome.toTypedArray())
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
     * @param max positive integer to apply as an exclusive upper limit to the range of random numbers
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

        // Convert selected random bits to an integer
        var randomNumber = randomBits?.let {
            runCatching {
                randomBits.toInt(2)
            }.getOrElse {
                throw IllegalStateException("Failed to convert bits string into integer")
            }
        } ?: throw IllegalStateException("Failed to generate random number. randomBits is null")

        // Random number picked from minNumberOfBits can be bigger than our maximum. We need to scale it down
        // For that, we check if randomNumber is bigger or equal to max, and if yes
        // we calculate the maximum number that can be represented with minNumberOfBits, and we multiply randomNumber by
        // the quotient of max to maxNumberWithMinBits
        if (randomNumber >= max) {
            val maxNumberWithMinBits = (2.0.pow(minNumberOfBits) - 1).toInt()
            randomNumber = (randomNumber * max.toFloat() / maxNumberWithMinBits).toInt() - 1
        }
        return randomNumber
    }

    /**
     * Generate a random float number in range of [0.0, 1.0]
     *
     * @param decimalPoints number of decimal points that the generated float number should have
     * @return generated Float value
     */
    private fun getRandomFloat(decimalPoints: Int): Float {
        // Calculate a minimum number of bits needed to represent the float number with given decimal points
        val minNumberOfBits = Integer.toBinaryString(10.0.pow(decimalPoints).toInt()).length
        val endIndex = randomNumberCursorPosition + minNumberOfBits
        // Get minNumberOfBits bits from a file starting at randomNumberCursorPosition
        randomBytesString?.let { randomBytesString ->
            if (endIndex >= randomBytesString.length) {
                this.randomBytesString = object {}.javaClass.getResource("random/${++currentRandomBitsFileNumber}")?.readText()
            }
            return getRandomFloatValue(randomBytesString, minNumberOfBits)
        } ?: throw IllegalStateException("Failed to generate random number. randomBits is null")
    }

    private fun getRandomFloatValue(randomBytesString: String, minNumberOfBits: Int): Float {
        val randomBits =
            runCatching {
                randomBytesString.substring(
                    randomNumberCursorPosition,
                    randomNumberCursorPosition + minNumberOfBits
                )
            }.getOrElse {
                throw IllegalStateException("Ran out of random bits in a file")
            }
        // Update cursor position so that the next time this method is called, it fetches unused bits from the file
        randomNumberCursorPosition += minNumberOfBits
        // Convert selected random bits to an integer and return it
        runCatching {
            return randomBits.toInt(2).toFloat() / 100
        }.getOrElse {
            throw IllegalStateException("Failed to convert bits string into integer")
        }
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