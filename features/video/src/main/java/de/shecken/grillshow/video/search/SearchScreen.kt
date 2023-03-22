package de.shecken.grillshow.video.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.shecken.grillshow.shared.compose.theming.AppTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun VideoScreen2(viewModel: SearchViewModel = getViewModel()) {
    VideoScreen2(
        onScreenClicked = { viewModel.onBackButtonClick() }
    )
}

@Composable
private fun VideoScreen2(onScreenClicked: () -> Unit) {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onScreenClicked() }
        ) {
            Text(
                text = "Video Screen 2",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .align(Center)
            )
        }
    }
}

@Preview
@Composable
fun VideoScreen2Preview() {
    VideoScreen2(
        onScreenClicked = { }
    )
}
