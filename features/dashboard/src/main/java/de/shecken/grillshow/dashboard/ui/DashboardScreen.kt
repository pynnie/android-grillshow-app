@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.dashboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import de.shecken.grillshow.dashboard.R
import de.shecken.grillshow.dashboard.vo.CategoryVo
import de.shecken.grillshow.dashboard.vo.RecipeListItemVo
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.ui.FavIconButton
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import org.koin.androidx.compose.getViewModel

@Composable
internal fun DashboardScreen(viewModel: DashboardViewModel = getViewModel()) {
    val state by viewModel.dashboardScreenState.collectAsStateWithLifecycle()
    DashboardScreen(state = state)
}

@Composable
private fun DashboardScreen(
    state: DashboardSceenState
) {
    Scaffold(topBar = { DashboardTopBar() }) { padding ->
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
                onRecipeClick = state.onRecipeClick
            )
            is DashboardSceenState.Failure -> Error()
        }
    }
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

@Composable
private fun DashboardTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.dashboard_title)) })
}

@Composable
private fun CategoryList(
    categories: List<CategoryVo>,
    onFavIconClick: (String, Boolean) -> Unit,
    onRecipeClick: (RecipeListItemVo) -> Unit
) {
    LazyColumn {
        items(items = categories) { category ->
            HorizontalRecipeList(
                title = category.title,
                recipes = category.recipes,
                onFavIconClick = onFavIconClick,
                onRecipeClick = onRecipeClick
            )
        }
    }
}

@Composable
private fun HorizontalRecipeList(
    title: String,
    recipes: List<RecipeListItemVo>,
    onFavIconClick: (String, Boolean) -> Unit,
    onRecipeClick: (RecipeListItemVo) -> Unit,
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(items = recipes) { recipe ->
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
    onRecipeClick: (RecipeListItemVo) -> Unit
) {
    val itemWidth = with(LocalDensity.current) { 640.toDp() }
    val itemHeight = with(LocalDensity.current) { 480.toDp() }
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(itemWidth)
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onRecipeClick(recipe) }) {
            AsyncImage(
                modifier = Modifier
                    .height(itemHeight)
                    .align(Alignment.Center),
                model = recipe.imageUrl,
                contentDescription = null
            )
            FavIconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                recipeId = recipe.id,
                isFavorite = recipe.isFavorite,
                onClick = onFavIconClick
            )
        }

        Text(
            text = recipe.title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
private fun TopBarPreview() {
    GrillshowTheme {
        DashboardTopBar()
    }
}