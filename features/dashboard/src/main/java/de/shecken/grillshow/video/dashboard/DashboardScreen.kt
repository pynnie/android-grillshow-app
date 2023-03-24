@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.video.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.shecken.grillshow.repository.video.PlayList
import de.shecken.grillshow.shared.ui.MainBottomBar
import org.koin.androidx.compose.getViewModel

@Composable
internal fun DashboardScreen(viewModel: DashboardViewModel = getViewModel()) {
    val state by viewModel.dashboardSceenState.collectAsState()
    DashboardScreen(
        onScreenClicked = { viewModel.onScreenClicked() }, state = state
    )
}

@Composable
private fun DashboardScreen(
    onScreenClicked: () -> Unit, state: DashboardSceenState
) {

    Scaffold(bottomBar = {
        MainBottomBar(onDashboardClick = { /*TODO*/ },
            onSearchClick = { /*TODO*/ },
            onFavoritesClick = { /*TODO*/ })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HandleScreenState(state = state)
        }
    }
}

@Composable
private fun HandleScreenState(state: DashboardSceenState) {
    when (state) {
        is DashboardSceenState.Loading -> LoadingIndicator()
        is DashboardSceenState.Success -> PlayLists(playLists = state.playlistItems)
        is DashboardSceenState.Failure -> Error()
    }
}

@Composable
fun Error() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Error!")
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun PlayLists(playLists: List<PlayList>) {
    Text(text = "Dashboard")
}

