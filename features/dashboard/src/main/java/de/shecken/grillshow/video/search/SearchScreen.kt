package de.shecken.grillshow.video.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
internal fun SearchScreen(viewModel: SearchViewModel = getViewModel()) {
    SearchScreen(
        onScreenClicked = { viewModel.onBackButtonClick() }
    )
}

@Composable
private fun SearchScreen(onScreenClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onScreenClicked() }
    ) {
        Text(
            text = "Search Screen",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Center)
        )
    }
}