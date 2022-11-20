// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

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
    val shouldShowVerticalBorder = (cell.cellId+1)%(worldSize/10)==0
    val shouldShowHorizontalBorder = cell.cellId > (worldSize-1) && cell.cellId%(worldSize*worldSize/10)<worldSize

    Box(
        modifier = Modifier
            .size(size.dp)
            .background(
                when (cell.type) {
                    CellType.Land ->  Color(0xffa5d6a7)
                    CellType.Water -> Color.Blue
                    CellType.Rock -> Color.DarkGray
                }
            )
            .border(
                bottom = Border(if(shouldShowHorizontalBorder) 1.dp else 0.dp, Color(0xff43a047)),
                end = Border(if(shouldShowVerticalBorder) 1.dp else 0.dp, Color(0xff43a047))
            )
    )
}

@Composable
@Preview
fun OrganismsLayer(
    worldParams: WorldParams,
    organisms: List<Organism>
) {
    val windowSize = 800f
    val cellSize = windowSize / worldParams.worldSize

    Box(modifier = Modifier.size(windowSize.dp)) {
        for (organism in organisms) {
            OrganismUi(
                organism = organisms[organism.id],
                size = windowSize / worldParams.worldSize,
                coordinates = (organism.cellNumber % worldParams.worldSize) * cellSize
                        to organism.cellNumber / worldParams.worldSize * cellSize
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
            radius = size/2.5.toFloat(),
            color = organism.color,
        )
    }
}

@Composable
@Preview
fun App() {
    val worldParams = getDefaultWorldParams()
    var buttonText by remember { mutableStateOf("Run") }
    val cells = remember { mutableStateOf(mutableListOf<Cell>()) }
    val organisms = remember { mutableStateOf(mutableListOf<Organism>()) }
    val generation = remember { mutableStateOf(0) }

    for (i in 0..worldParams.worldSize * worldParams.worldSize) {
        val xCoordinate = i % worldParams.worldSize
        val yCoordinate = i / worldParams.worldSize
        cells.value.add(
            Cell(
                cellId = i,
                coordinates = xCoordinate to yCoordinate,
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
            cellNumber = initialPopulatedCells[i],
            age = 1,
            energy = 100,
            hunger = 0,
            thirst = 0,
            hornyness = 0,
            sleepiness = 0
        )
        organisms.value.add(organism)
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
                    Box {
                        World(
                            worldParams = worldParams,
                            cells = cells.value
                        )
                        OrganismsLayer(
                            worldParams = worldParams,
                            organisms = organisms.value
                        )
                    }
                    Button(
                        onClick = {
                            buttonText = if (buttonText == "Run") "Stop" else "Run"
                        }
                    ) {
                        Text(buttonText)
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
                        Text("Cell id of organism:")
                        Text(organisms.value.first().cellNumber.toString())
                    }
                }
            }
        }
    }
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
    worldSize = 200,
    initialPopulation = 200,
    genomeMaxLength = 2,
    maxNumberOfNeurons = 10,
    mutationRate = 0.01f
)

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