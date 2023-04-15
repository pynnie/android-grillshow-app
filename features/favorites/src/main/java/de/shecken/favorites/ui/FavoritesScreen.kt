@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.favorites.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.shecken.grillshow.favorites.R
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import de.shecken.grillshow.shared.ui.ListDivider
import de.shecken.grillshow.shared.ui.RecipeListItem
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
            RecipeListItem(
                recipeId = item.id,
                title = item.title,
                imageUrl = item.imageUrl,
                onItemClick = state.onItemClick
            )
            if (index < state.favoriteList.lastIndex) {
                ListDivider()
            }
        }
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
