package de.shecken.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
internal fun FavoriteScreen(viewModel: FavoritesViewModel = getViewModel()) {
    SearchScreen()
}

@Composable
private fun SearchScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Favorites Screen",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Center)
        )
    }

}