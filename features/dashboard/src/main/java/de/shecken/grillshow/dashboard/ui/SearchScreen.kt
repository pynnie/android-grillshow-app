package de.shecken.grillshow.dashboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.shecken.grillshow.dashboard.R
import de.shecken.grillshow.vo.SearchResultVo
import de.shecken.grillshow.shared.ui.ListDivider
import de.shecken.grillshow.shared.ui.RecipeListItem

@Composable
internal fun SearchScreen(state: DashboardSceenState.SearchScreenState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            is DashboardSceenState.SearchScreenState.Success -> SearchResultList(
                recipes = state.recipes,
                onRecipeClick = state.onRecipeClick
            )
            is DashboardSceenState.SearchScreenState.Empty -> EmptyScreen()
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
private fun SearchResultList(
    recipes: List<SearchResultVo>,
    onRecipeClick: (String) -> Unit
) {
    LazyColumn {
        itemsIndexed(recipes) { index, recipeItem ->
            RecipeListItem(
                recipeId = recipeItem.id,
                title = recipeItem.title,
                imageUrl = recipeItem.imageUrl,
                onItemClick = onRecipeClick
            )
            if (index < recipes.lastIndex) {
                ListDivider()
            }
        }
    }
}