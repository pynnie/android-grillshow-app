@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package de.shecken.grillshow.dashboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import de.shecken.grillshow.dashboard.R
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.ui.Divider
import de.shecken.grillshow.shared.ui.FavIconButton
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import de.shecken.grillshow.shared.ui.Message
import de.shecken.grillshow.vo.CategoryVo
import de.shecken.grillshow.vo.RecipeListItemVo
import org.koin.androidx.compose.getViewModel

@Composable
internal fun DashboardScreen(viewModel: DashboardViewModel = getViewModel()) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    DashboardScreen(
        state = state,
        onSearchQueryChanged = viewModel::onQueryChange,
        onToggleSearchMode = viewModel::toggleSearchMode
    )
}

@Composable
private fun DashboardScreen(
    state: DashboardSceenState,
    onSearchQueryChanged: (String) -> Unit,
    onToggleSearchMode: (Boolean) -> Unit
) {
    Scaffold(topBar = {
        DashboardTopBar(
            state = state,
            onSearchQueryChanged = onSearchQueryChanged,
            onToggleSearchMode = onToggleSearchMode
        )
    }) { padding ->
        HandleScreenState(modifier = Modifier.padding(padding), state = state)
    }
}

@Composable
private fun HandleScreenState(modifier: Modifier, state: DashboardSceenState) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            is DashboardSceenState.Loading -> FullScreenLoadingIndicator()
            is DashboardSceenState.Success -> CategoryList(
                categories = state.categories,
                onFavIconClick = state.onFavIconClick,
                onRecipeClick = state.onRecipeClick,
                onCategoryClick = state.onCategoryClick
            )

            is DashboardSceenState.Failure -> Failure(state.onReloadClick)
            is DashboardSceenState.SearchScreenState -> SearchScreen(state = state)
        }
    }
}

@Composable
private fun Failure(onReloadClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Message(
            title = stringResource(id = R.string.dashboard_error_title),
            message = stringResource(id = R.string.dashboard_error_message),
            buttonText = stringResource(id = R.string.dashboard_error_button),
            imageRes = R.drawable.ninja,
            onButtonClick = onReloadClick
        )
    }
}

@Composable
private fun DashboardTopBar(
    state: DashboardSceenState,
    onSearchQueryChanged: (String) -> Unit,
    onToggleSearchMode: (Boolean) -> Unit
) {
    if (state is DashboardSceenState.SearchScreenState) {
        SearchTopBar(
            modifier = Modifier.padding(top = 8.dp),
            state = state,
            onSearchQueryChanged = onSearchQueryChanged,
            onToggleSearchMode = onToggleSearchMode
        )
    } else {
        DefaultTopBar(onToggleSearchMode = onToggleSearchMode)
    }
}

@Composable
private fun SearchTopBar(
    modifier: Modifier = Modifier,
    state: DashboardSceenState.SearchScreenState,
    onSearchQueryChanged: (String) -> Unit,
    onToggleSearchMode: (Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    TopAppBar(
        modifier = modifier.focusRequester(focusRequester),
        title = {},
        actions = {
            var searchQuery by remember { mutableStateOf(state.query) }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { onToggleSearchMode(false) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clear),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = ""
                )
            }
            SearchField(
                modifier = Modifier.weight(1f),
                searchQuery = searchQuery,
                onSearchQueryChanged = { query ->
                    onSearchQueryChanged(query)
                    searchQuery = query
                })

        }
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun DefaultTopBar(onToggleSearchMode: (Boolean) -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(top = 8.dp),
        title = { Text(text = stringResource(id = R.string.dashboard_title)) },
        actions = {
            IconButton(onClick = { onToggleSearchMode(true) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = ""
                )
            }
        }
    )
}


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var textFieldValue by remember {
        mutableStateOf(searchQuery)
    }

    TextField(modifier = modifier
        .clip(RoundedCornerShape(64.dp)),
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onSearchQueryChanged(textFieldValue)
        },
        placeholder = {
            Text(text = stringResource(R.string.search_field_prompt))
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        textFieldValue = ""
                        onSearchQueryChanged("")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "",
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}

@Composable
private fun CategoryList(
    categories: List<CategoryVo>,
    onFavIconClick: (String, Boolean) -> Unit,
    onRecipeClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    LazyColumn {
        items(items = categories, key = { it.id }) { category ->
            HorizontalRecipeList(
                category = category,
                onFavIconClick = onFavIconClick,
                onRecipeClick = onRecipeClick,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
private fun HorizontalRecipeList(
    category: CategoryVo,
    onFavIconClick: (String, Boolean) -> Unit,
    onRecipeClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Text(text = category.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { onCategoryClick(category.id) }) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(items = category.recipes, key = { it.id }) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onFavIconClick = onFavIconClick,
                    onRecipeClick = onRecipeClick
                )
            }
        }
    }
}

@Composable
private fun RecipeItem(
    recipe: RecipeListItemVo,
    onFavIconClick: (String, Boolean) -> Unit,
    onRecipeClick: (String) -> Unit
) {
    val itemWidth = with(LocalDensity.current) { 640.toDp() }
    val itemHeight = with(LocalDensity.current) { 480.toDp() }

    Card(
        modifier = Modifier.width(itemWidth),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .clickable { onRecipeClick(recipe.id) }) {
            AsyncImage(
                modifier = Modifier
                    .height(itemHeight)
                    .align(Alignment.Center),
                model = recipe.imageUrl,
                contentDescription = null
            )
            FavIconButton(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.TopEnd),
                recipeId = recipe.id,
                isFavorite = recipe.isFavorite,
                onClick = onFavIconClick
            )
        }
        Divider()
        Text(
            modifier = Modifier.padding(8.dp),
            text = recipe.title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun RecipeItemPreview() {
    GrillshowTheme {
        RecipeItem(
            recipe = RecipeListItemVo("", "Item1", "", false),
            onFavIconClick = { _, _ -> },
            onRecipeClick = {}
        )
    }
}