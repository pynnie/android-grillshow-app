@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.shop.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.search.R
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.ui.FavIconButton
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import de.shecken.grillshow.shared.ui.ListDivider
import de.shecken.grillshow.shared.ui.debugPlaceholder
import org.koin.androidx.compose.getViewModel

@Composable
internal fun SearchScreen(viewModel: SearchViewModel = getViewModel()) {
    val state by viewModel.searchScreenState.collectAsStateWithLifecycle()
    SearchScreen(state = state, onSearchQueryChanged = viewModel::onQueryChange)
}

@Composable
private fun SearchScreen(state: SearchScreenState, onSearchQueryChanged: (String) -> Unit) {
    Scaffold(topBar = { SearchTopBar() }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
            ) {
                var searchQuery by remember { mutableStateOf("") }
                SearchBar(searchQuery = searchQuery, onSearchQueryChanged = { query ->
                    onSearchQueryChanged(query)
                    searchQuery = query
                })

                HandleScreenState(state = state)
            }
        }


    }
}

@Composable
private fun HandleScreenState(state: SearchScreenState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            is SearchScreenState.Loading -> FullScreenLoadingIndicator()
            is SearchScreenState.Success -> SearchResultList(
                recipes = state.recipes,
                onFavIconClick = state.onFavIconClick,
                onRecipeClick = state.onRecipeClick
            )
            is SearchScreenState.Empty -> EmptyScreen()
        }
    }
}

@Composable
private fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.search_empty_message)
        )
    }
}

@Composable
private fun SearchTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.search_title)) },
    )
}

@Composable
private fun SearchResultList(
    recipes: List<Recipe>,
    onFavIconClick: (String, Boolean) -> Unit,
    onRecipeClick: (Recipe) -> Unit
) {
    LazyColumn {
        itemsIndexed(recipes) { index, recipeItem ->
            ResultItem(
                recipe = recipeItem, onFavIconClick = onFavIconClick, onRecipeClick = onRecipeClick
            )
            if (index < recipes.lastIndex) {
                ListDivider()
            }
        }
    }
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        placeholder = {
            Text(text = stringResource(R.string.search_field_prompt))
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = { onSearchQueryChanged("") }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = "",
                    )
                }
            }
        }
    )
}

@Composable
private fun ResultItem(
    recipe: Recipe, onFavIconClick: (String, Boolean) -> Unit, onRecipeClick: (Recipe) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onRecipeClick(recipe) }, verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(0.3f)
        ) {
            AsyncImage(
                model = recipe.thumbnailUrl,
                contentDescription = "",
                placeholder = debugPlaceholder(
                    debugPreview = R.drawable.default_thumbnail
                )
            )

            FavIconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                recipeId = recipe.id,
                isFavorite = recipe.isFavorite,
                onClick = onFavIconClick
            )
        }

        Text(
            modifier = Modifier
                .weight(0.7f)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = recipe.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    GrillshowTheme {
        SearchBar(searchQuery = "", onSearchQueryChanged = {})
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    GrillshowTheme {
        SearchScreen(
            state = SearchScreenState.Success(
                listOf(
                    Recipe(
                        id = "1",
                        title = "Test",
                        thumbnailUrl = "https://www.grillshow.de/wp-content/uploads/2021/03/IMG_20210307_180000.jpg",
                        isFavorite = false,
                        description = "bla blub"
                    ),
                    Recipe(
                        id = "1",
                        title = "Test2",
                        thumbnailUrl = "https://www.grillshow.de/wp-content/uploads/2021/03/IMG_20210307_180000.jpg",
                        isFavorite = false,
                        description = "bla blub"
                    ),
                    Recipe(
                        id = "1",
                        title = "Test3",
                        thumbnailUrl = "https://www.grillshow.de/wp-content/uploads/2021/03/IMG_20210307_180000.jpg",
                        isFavorite = false,
                        description = "bla blub"
                    ),
                ), { da, dsa -> }, {}),
            onSearchQueryChanged = {})
    }
}