package de.shecken.grillshow.video.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import de.shecken.grillshow.repository.video.PlayList
import de.shecken.grillshow.repository.video.PlaylistItem
import de.shecken.grillshow.shared.compose.theming.AppTheme
import de.shecken.grillshow.video.R
import org.koin.androidx.compose.getViewModel

@Composable
internal fun DashboardScreen(viewModel: DashboardViewModel = getViewModel()) {
    val state by viewModel.dashboardSceenState.collectAsState()
    DashboardScreen(
        onScreenClicked = { viewModel.onScreenClicked() },
        state = state
    )
}

@Composable
private fun DashboardScreen(
    onScreenClicked: () -> Unit,
    state: DashboardSceenState
) {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
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
    R.drawable.default_thumbnail
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
    LazyColumn {
        items(items = playLists) { playList ->
            PlayListRow(playList = playList)
        }
    }
}

@Composable
fun PlayListRow(playList: PlayList) {
    Column {
        Text(text = playList.title)

        LazyRow {
            items(items = playList.items) { playList ->
                PlayListRowItem(playListItem = playList)
            }
        }
    }

}

@Composable
fun PlayListRowItem(playListItem: PlaylistItem) {
    Box(modifier = Modifier.clip(RoundedCornerShape(8)))
    Column() {
        Image(painter = rememberAsyncImagePainter(
            model = playListItem.thumbnailUrl,
            placeholder = painterResource(id = R.drawable.default_thumbnail)
        ), contentDescription = "" )

        Row {
            Text(text = playListItem.title)
        }
    }
}

@Preview
@Composable
fun PlayListRowItemPreview() {
    PlayListRowItem(
        playListItem = PlaylistItem(
            "Neues Rezept: Lustiger Bosniak #grillshow #rezept #bbq",
            "beschreibung",
            "url"
        )
    )
}

