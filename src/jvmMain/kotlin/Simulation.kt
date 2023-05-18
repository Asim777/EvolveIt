import androidx.compose.ui.graphics.Color
import data.*
import kotlin.math.pow

class Simulation(private val worldParams: WorldParams) {

    private var randomNumberCursorPosition = 0
    private var currentRandomBitsFileNumber = 0
    private var randomBitsString = object {}.javaClass.getResource("random/0")?.readText()
    private val world = hashMapOf<Int, HashMap<Int, Cell>>()
    private val genePool = mutableListOf<Array<Gene>>()
    private val entities = mutableListOf<Entity>()

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
        println("Setup Finished")
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
                            get(getRandomInteger(size))
                        },
                        output = innerNeurons.plus(sinkNeurons).run {
                            get(getRandomInteger(size))
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
            entities.add(
                Entity(
                    id = i,
                    genome = genome,
                    color = genome.generateColor(),
                    coordinates = getRandomNonOccupiedCoordinate(),
                    age = 0,
                    energy = 100,
                    hunger = 0,
                    hornyness = 0
                )
            )
        }
    }

    /**
     * Returns a random coordinate in the World that is not currently occupied by any entity
     *
     * @return Coordinates - random non-occupied coordinate
     */
    private fun getRandomNonOccupiedCoordinate(): Coordinates {
        var coord: Coordinates
        do {
            coord = Coordinates(
                x = getRandomInteger(worldParams.worldSize),
                y = getRandomInteger(worldParams.worldSize)
            )
        } while (world[coord.x]?.get(coord.y)?.hasEntity ?: throw IllegalStateException("Cell is null"))
        return coord
    }

    /**
     * Generate a random number in range of [0, max]
     *
     * @param max positive integer to apply as an exclusive upper limit to the range of random numbers
     * @return generated Int value
     */
    private fun getRandomInteger(max: Int): Int {
        // Calculate a minimum number of bits needed to represent the maximum
        val minNumberOfBits = Integer.toBinaryString(max).length

        goToNextRandomNumberFile(minNumberOfBits)
        // Get minNumberOfBits bits from a file starting at randomNumberCursorPosition
        return getRandomNumber(minNumberOfBits, max)
    }

    /**
     * Generate a random float number in range of [0.0, 1.0]
     *
     * @param decimalPoints number of decimal points that the generated float number should have
     * @return generated Float value
     */
    private fun getRandomFloat(decimalPoints: Int): Float {
        val max = 10.0.pow(decimalPoints).toInt()
        // Calculate a minimum number of bits needed to represent the float number with given decimal points
        return getRandomInteger(max).toFloat() / 100
    }

    private fun goToNextRandomNumberFile(minNumberOfBits: Int) {
        val endIndex = randomNumberCursorPosition + minNumberOfBits
        randomBitsString?.let {
            if (endIndex >= it.length) {
                this.randomBitsString =
                    object {}.javaClass.getResource("random/${++currentRandomBitsFileNumber}")?.readText()
            }
        } ?: throw IllegalStateException("Failed to generate random number. randomBits is null")
    }

    private fun getRandomNumber(minNumberOfBits: Int, max: Int): Int {
        val randomBits =
            runCatching {
                randomBitsString?.substring(
                    randomNumberCursorPosition,
                    randomNumberCursorPosition + minNumberOfBits
                ) ?: throw IllegalStateException("Failed to generate random number. randomBits is null")
            }.getOrElse {
                throw IllegalStateException("Ran out of random bits in a file")
            }
        // Update cursor position so that the next time this method is called, it fetches unused bits from the file
        randomNumberCursorPosition += minNumberOfBits
        // Convert selected random bits to an integer and return it
        runCatching {
            var randomNumber = randomBits.toInt(2)
            // Random number picked from minNumberOfBits can be bigger than our maximum. We need to scale it down
            // For that, we check if randomNumber is bigger or equal to max, and if yes
            // we calculate the maximum number that can be represented with minNumberOfBits, and we multiply randomNumber by
            // the quotient of max to maxNumberWithMinBits
            if (randomNumber >= max) {
                val maxNumberWithMinBits = (2.0.pow(minNumberOfBits) - 1).toInt()
                randomNumber = (randomNumber * max.toFloat() / maxNumberWithMinBits).toInt() - 1
            }
            return randomNumber
        }.getOrElse {
            throw IllegalStateException("Failed to convert bits string into integer")
        }
    }
}
