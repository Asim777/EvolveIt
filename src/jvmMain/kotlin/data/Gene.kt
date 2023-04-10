package data

class Gene(
    val input: Neuron,
    val output: Neuron,
    val weight: Float
) {
    override fun hashCode() = (input.hashCode()*0.5+output.hashCode()*0.5).toInt()
}