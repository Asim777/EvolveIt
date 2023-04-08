import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.WorldParams
import java.awt.Dimension

@Composable
@Preview
fun app() {
    Button(
        modifier = Modifier
            .wrapContentSize(),
        onClick = {
            Simulation(
                WorldParams(
                    worldSize = 1000,
                    initialPopulation = 1000,
                    genomeLength = 4,
                    foodAvailability = 0.5f,
                    mutationRate = 0.01f,
                    numberOfSensorNeurons = 20,
                    numberOfInnerNeurons = 12,
                    numberOfSinkNeurons = 20
                )
            ).setup()
        }) {
        Text("Start")
    }
}


fun main() = application {
    val icon = painterResource("sample.png")

    Window(
        onCloseRequest = ::exitApplication,
        title = "EvolveIt",
        icon = icon
    ) {
        window.size = Dimension(1300, 950)
        MaterialTheme(
            colors = if (isSystemInDarkTheme()) darkColors() else lightColors()
        ) {
            Surface(Modifier.fillMaxSize()) {
                app()
            }
        }
    }
}

