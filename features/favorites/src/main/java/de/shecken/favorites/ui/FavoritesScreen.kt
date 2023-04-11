@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.favorites.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import de.shecken.favorites.R
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import de.shecken.grillshow.shared.ui.ListDivider
import de.shecken.grillshow.shared.ui.debugPlaceholder
import org.koin.androidx.compose.getViewModel

@Composable
internal fun FavoriteScreen(viewModel: FavoritesViewModel = getViewModel()) {
    val state by viewModel.favoritesScreenState.collectAsStateWithLifecycle()

    FavoritesScreen(state = state)
}

@Composable
private fun FavoritesScreen(state: FavoritesScreenState) {
    Scaffold(topBar = { FavoritesTopBar() }) { padding ->
        HandleScreenState(modifier = Modifier.padding(padding), state = state)
    }
}

@Composable
private fun HandleScreenState(modifier: Modifier, state: FavoritesScreenState) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            is FavoritesScreenState.Loading -> FullScreenLoadingIndicator()
            is FavoritesScreenState.Success -> HandleSuccessState(state)
            is FavoritesScreenState.Empty -> Error()
        }
    }
}

@Composable
private fun HandleSuccessState(state: FavoritesScreenState.Success) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items = state.favoriteList) { index, item ->
            FavoriteItem(recipeFavoriteItem = item, onFavoriteItemClick = state.onItemClick)
            if (index < state.favoriteList.lastIndex) {
                ListDivider()
            }
        }
    }
}

@Composable
private fun FavoriteItem(recipeFavoriteItem: Recipe, onFavoriteItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onFavoriteItemClick(recipeFavoriteItem.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.weight(0.3f),
            model = recipeFavoriteItem.thumbnailUrl,
            contentDescription = "",
            placeholder = debugPlaceholder(
                debugPreview = R.drawable.default_thumbnail
            )
        )
        Text(
            modifier = Modifier
                .weight(0.7f)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = recipeFavoriteItem.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun FavoritesTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.favorites_title)) },
    )
}

@Composable
private fun Error() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Error!")
    }
}

@Preview
@Composable
private fun FavoriteItemPreview() {
    GrillshowTheme {
        val recipe = Recipe(
            id = "1",
            title = "Das perfekte Steak! DER KLAPPO GRILL in Action | Die Grillshow Shorts #steak  #bbq #outdoorcooking",
            thumbnailUrl = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
            isFavorite = false,
            description = "bla bla"
        )
        FavoriteItem(recipeFavoriteItem = recipe, onFavoriteItemClick = {})
    }

}
