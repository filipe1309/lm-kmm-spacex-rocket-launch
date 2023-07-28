import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import filipe1309.appacademy.kmm.shared.entity.RocketLaunch
import filipe1309.appacademy.kmm.shared.network.SpaceXApi
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val launches: List<RocketLaunch> = runBlocking { SpaceXApi().getAllLaunches() }
    MaterialTheme {
        RocketLaunchList(launches)
    }
}

@Composable
fun RocketLaunchList(launches: List<RocketLaunch>) {
    LazyColumn {
        item {
            Text(
                text = "Launches",
                style = MaterialTheme.typography.h4
            )
        }
        items(launches) { launch ->
            RocketLaunchRow(launch)
        }
    }
}

@Composable
fun RocketLaunchRow(launch: RocketLaunch) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${launch.missionName} - ${launch.launchYear}")
        }
    }
}

expect fun getPlatformName(): String
