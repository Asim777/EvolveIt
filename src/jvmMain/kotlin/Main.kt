import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import java.awt.Dimension
import java.util.concurrent.TimeUnit

@Composable
@Preview
fun World(
    worldParams: WorldParams,
    cells: List<Cell>
) {
    val windowSize = 800f

    Box(
        modifier = Modifier.size(windowSize.dp)
    ) {
        Column {
            for (y in 0 until worldParams.worldSize) {
                Row {
                    for (x in 0 until worldParams.worldSize) {
                        val cellId = y * worldParams.worldSize + x
                        CellUi(
                            size = windowSize / worldParams.worldSize,
                            cell = cells[cellId],
                            worldSize = worldParams.worldSize
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CellUi(
    size: Float,
    cell: Cell,
    worldSize: Int
) {
    if (worldSize >= 10) {
        val shouldShowVerticalBorder = cell.coordinates.x % (worldSize / 10) == 0
        val shouldShowHorizontalBorder = cell.coordinates.y % (worldSize / 10) == 0
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(
                    when (cell.type) {
                        CellType.Land -> Color(0xffa5d6a7)
                        CellType.Water -> Color.Blue
                        CellType.Rock -> Color.DarkGray
                    }
                )
                .border(
                    top = Border(if (shouldShowHorizontalBorder) 1.dp else 0.dp, Color(0xff43a047)),
                    start = Border(if (shouldShowVerticalBorder) 1.dp else 0.dp, Color(0xff43a047))
                )
        )
    }
}

@Composable
@Preview
fun OrganismsLayer(
    worldParams: WorldParams,
    organisms: SnapshotStateList<Organism>
) {
    val windowSize = 800f
    val cellSize = windowSize / worldParams.worldSize

    Box(modifier = Modifier.size(windowSize.dp)) {
        for (organism in organisms) {
            OrganismUi(
                organism = organisms[organism.id],
                size = windowSize / worldParams.worldSize,
                coordinates = (organism.coordinates.x) * cellSize to organism.coordinates.y * cellSize
            )
        }
    }
}

@Composable
@Preview
fun OrganismUi(
    organism: Organism,
    size: Float,
    coordinates: Pair<Float, Float>
) {
    Canvas(
        modifier = Modifier
            .size(size.dp)
            .offset(x = coordinates.first.dp, y = coordinates.second.dp)
    ) {
        drawCircle(
            radius = size / 2.5.toFloat(),
            color = organism.color,
        )
    }
}

@Composable
@Preview
fun App() {
    val worldParams by remember { mutableStateOf(getDefaultWorldParams()) }
    val testVariable by remember { mutableStateOf(TestVariableType(0)) }
    var runButtonText by remember { mutableStateOf("Run") }
    val errorText = remember { mutableStateOf("") }
    val cells = remember { mutableStateOf(mutableListOf<Cell>()) }
    val organisms = remember { mutableStateListOf<Organism>() }
    val generation = remember { mutableStateOf(0) }

    for (i in 0..worldParams.worldSize * worldParams.worldSize) {
        val xCoordinate = i % worldParams.worldSize
        val yCoordinate = i / worldParams.worldSize
        cells.value.add(
            Cell(
                coordinates = Coordinate(x = xCoordinate, y = yCoordinate),
                type = CellType.Land
            )
        )
    }
    val initialPopulatedCells = (0 until worldParams.worldSize * worldParams.worldSize)
        .shuffled()
        .take(worldParams.initialPopulation)
    for (i in 0 until worldParams.initialPopulation) {
        val organism = Organism(
            id = i,
            genes = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6)),
            color = Color.Red,
            coordinates = cells.value[initialPopulatedCells[i]].coordinates,
            age = 1,
            energy = 100,
            hunger = 0,
            thirst = 0,
            hornyness = 0,
            sleepiness = 0
        )
        organisms.add(organism)
    }

    ContextMenuArea(
        items = {
            listOf(
                ContextMenuItem("Start new simulation") {

                },
                ContextMenuItem("Speed up") {

                },
                ContextMenuItem("Speed down") {

                }
            )
        }
    ) {
        Scaffold {
            Row {
                // Left pane with World and Controls
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // World
                    Box {
                        World(
                            worldParams = worldParams,
                            cells = cells.value
                        )
                        OrganismsLayer(
                            worldParams = worldParams,
                            organisms = organisms
                        )
                    }
                    // Run button
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            if (runButtonText == "Run") {
                                runButtonText = "Stop"

                                println("testVariable.internalVariable: ${testVariable.internalVariable}")
                                CoroutineScope(Dispatchers.IO).launch {
                                    repeat(50) {
                                        runOneStep(organisms, worldParams.worldSize)
                                        delay(TimeUnit.MILLISECONDS.toMillis(100))
                                    }
                                    runButtonText = "Run"
                                }
                            } else {
                                runButtonText = "Run"
                                stopSimulation()
                            }
                        }
                    ) {
                        Text(runButtonText)
                    }
                }
                // Right pane with input parameters and output readings
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row {
                        Text("Generation:")
                        Text(generation.value.toString())
                    }
                    Row {
                        var worldSizeText by remember { mutableStateOf(TextFieldValue(worldParams.worldSize.toString())) }

                        Text("World Size:")
                        TextField(
                            value = worldSizeText,
                            onValueChange = {
                                var inputNumber: Int

                                kotlin.runCatching {
                                    inputNumber = if (it.text != "") it.text.toInt() else 0
                                    when {
                                        inputNumber >= 10 -> {
                                            errorText.value = ""
                                            worldParams.worldSize = inputNumber
                                            worldSizeText = TextFieldValue(inputNumber.toString())
                                        }
                                        inputNumber in 1..9 -> {
                                            errorText.value = "Please enter a number bigger or equal to 10"
                                            worldSizeText = TextFieldValue(inputNumber.toString())
                                        }
                                        else -> {
                                            errorText.value = "Please enter a number bigger or equal to 10"
                                            worldSizeText = TextFieldValue("0")
                                        }
                                    }
                                }.getOrElse {
                                    errorText.value = "Please enter a valid number"
                                    worldParams.worldSize = 0
                                }
                            },
                            label = { Text("Enter number bigger than 10 here") },
                            placeholder = { Text(text = "World Size") }
                        )
                    }
                    Row {
                        var text by remember { mutableStateOf(TextFieldValue(testVariable.internalVariable.toString())) }
                        Text("Test Field 2:")
                        TextField(
                            value = text,
                            label = { Text(text = "Number Input Type") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = { it ->
                                testVariable.internalVariable = it.text.toInt()
                                text = it
                            }
                        )
                    }
                    Row {
                        Text(errorText.value)
                    }
                }
            }
        }
    }
}

fun runOneStep(organisms: SnapshotStateList<Organism>, worldSize: Int) {
    val updatedOrganisms = organisms.toTypedArray()
    // icOccupied contains array of arrays of booleans, where first level represents rows, and second level represents
    // cells and their boolean values represent whether the cell is occupied or not
    val isOccupied = Array(worldSize) { Array(worldSize) { false } }

    organisms.forEach { organism ->
        isOccupied[organism.coordinates.x][organism.coordinates.y] = true
    }

    updatedOrganisms.forEach { organism ->
        moveToRandomDirection(organism, worldSize, isOccupied)
    }
    organisms.clear()
    organisms.addAll(updatedOrganisms)
}

fun moveToRandomDirection(
    organism: Organism,
    worldSize: Int,
    isOccupied: Array<Array<Boolean>>,
    allowedDirections: List<MovementDirection> = listOf(
        MovementDirection.NotMove,
        MovementDirection.North,
        MovementDirection.NorthEast,
        MovementDirection.SouthEast,
        MovementDirection.South,
        MovementDirection.SouthWest,
        MovementDirection.West,
        MovementDirection.NorthWest
    )
) {
    fun move(newCoordinates: Coordinate) {
        if (newCoordinates != organism.coordinates) {
            if (isWithinWorldBoundaries(newCoordinates, worldSize) && !isOccupied[newCoordinates.x][newCoordinates.y]) {
                isOccupied[organism.coordinates.x][organism.coordinates.y] = false
                //println("Organism ${organism.id} moved from ${organism.coordinates.x} ${organism.coordinates.y} to ${newCoordinates.x} ${newCoordinates.y}")
                organism.coordinates = newCoordinates
                isOccupied[newCoordinates.x][newCoordinates.y] = true
            } else {
                moveToRandomDirection(
                    organism,
                    worldSize,
                    isOccupied,
                    allowedDirections.filter { it != MovementDirection.North })
            }
        } else {
            //println("Organism ${organism.id} stayed in place in coordinates ${organism.coordinates.x} ${organism.coordinates.y}")
        }
    }

    val newCoordinates = when (allowedDirections.toList().shuffled().first()) {
        MovementDirection.NotMove -> organism.coordinates
        MovementDirection.North -> Coordinate(organism.coordinates.x, organism.coordinates.y - 1)
        MovementDirection.NorthEast -> Coordinate(organism.coordinates.x + 1, organism.coordinates.y - 1)
        MovementDirection.East -> Coordinate(organism.coordinates.x + 1, organism.coordinates.y)
        MovementDirection.SouthEast -> Coordinate(organism.coordinates.x + 1, organism.coordinates.y + 1)
        MovementDirection.South -> Coordinate(organism.coordinates.x, organism.coordinates.y + 1)
        MovementDirection.SouthWest -> Coordinate(organism.coordinates.x - 1, organism.coordinates.y + 1)
        MovementDirection.West -> Coordinate(organism.coordinates.x - 1, organism.coordinates.y)
        MovementDirection.NorthWest -> Coordinate(organism.coordinates.x - 1, organism.coordinates.y - 1)
    }
    move(newCoordinates)
}

fun isWithinWorldBoundaries(coordinates: Coordinate, worldSize: Int) =
    coordinates.x in 0 until worldSize && coordinates.y in 0 until worldSize

fun stopSimulation() {

}

fun main() = application {
    val icon = painterResource("sample.png")

    Tray(
        icon = icon,
        menu = {
            Item("Quit App", onClick = ::exitApplication)
            Item("Item 2", onClick = ::exitApplication)
            Item("Another item", onClick = ::exitApplication)
        }
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Evolution",
        icon = icon
    ) {
        window.minimumSize = Dimension(1300, 950)
        MaterialTheme(
            colors = if (isSystemInDarkTheme()) darkColors() else lightColors()
        ) {
            val contextMenuRepresentation = if (isSystemInDarkTheme()) {
                DarkDefaultContextMenuRepresentation
            } else {
                LightDefaultContextMenuRepresentation
            }
            CompositionLocalProvider(LocalContextMenuRepresentation provides contextMenuRepresentation) {
                Surface(Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }
}

fun getDefaultWorldParams() = WorldParams(
    worldSize = 100,
    initialPopulation = 200,
    genomeMaxLength = 2,
    maxNumberOfNeurons = 10,
    mutationRate = 0.01f
)

class TestVariableType(
    var internalVariable: Int
)
enum class MovementDirection {
    NotMove,
    North,
    NorthEast,
    East,
    SouthEast,
    South,
    SouthWest,
    West,
    NorthWest;
}

//            Column {
//
//                TextField(
//                    value = text.value,
//                    onValueChange = { text.value = it },
//                    label = { Text("Enter text here") },
//                    placeholder = { Text(text = "Input") }
//                )
//
//                Spacer(Modifier.height(16.dp))
//
//                SelectionContainer {
//                    Text("Hello World!")
//                }
//
//                Button(onClick = {
//                    buttonText = if (buttonText == "Hello, Desktop!") "Hello, World!" else "Hello, Desktop!"
//                }) {
//                    Text(buttonText)
//                }
//
//                Image(
//                    painter = painterResource("sample.png"),
//                    contentDescription = "Sample",
//                    modifier = Modifier.fillMaxSize()
//                )
//            }