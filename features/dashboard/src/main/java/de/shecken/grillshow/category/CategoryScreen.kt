@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.category

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
import de.shecken.grillshow.dashboard.R
import de.shecken.grillshow.shared.ui.*
import de.shecken.grillshow.vo.RecipeListItemVo
import org.koin.androidx.compose.getViewModel

@Composable
fun CategoryScreen(viewModel: CategoryViewModel = getViewModel()) {

    val state by viewModel.screenState.collectAsStateWithLifecycle()
    CategoryScreen(state = state, onBackButtonClick = viewModel::goBack)
}

@Composable
private fun CategoryScreen(
    state: CategoryScreenState,
    onBackButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CategoryTopBar(
                title = (state as? CategoryScreenState.Success)?.category?.title ?: "",
                onBackButtonClick = onBackButtonClick
            )
        }) { padding ->
        HandleScreenState(modifier = Modifier.padding(padding), state = state)
    }
}


@Composable
private fun HandleScreenState(modifier: Modifier, state: CategoryScreenState) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            is CategoryScreenState.Loading -> FullScreenLoadingIndicator()
            is CategoryScreenState.Success -> SuccessState(state)
            is CategoryScreenState.Failure -> Failure(state.onReloadButtonClick)
        }
    }
}

@Composable
private fun Failure(onReloadButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Message(
            title = stringResource(id = R.string.category_error_title),
            message = stringResource(id = R.string.category_error_message),
            buttonText = stringResource(id = R.string.category_error_button),
            imageRes = R.drawable.ninja,
            onButtonClick = onReloadButtonClick
        )
    }
}

@Composable
private fun CategoryTopBar(
    title: String,
    onBackButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            BackButton(onClick = onBackButtonClick)
        },
    )
}

@Composable
private fun SuccessState(state: CategoryScreenState.Success) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))

        RecipeList(recipes = state.category.recipes, onRecipeClick = state.onRecipeClick)
    }
}

@Composable
private fun RecipeList(recipes: List<RecipeListItemVo>, onRecipeClick: (String) -> Unit) {
    LazyColumn {
        itemsIndexed(items = recipes) { index, recipeItem ->
            with(recipeItem) {
                RecipeListItem(
                    recipeId = id,
                    title = title,
                    imageUrl = imageUrl,
                    onItemClick = onRecipeClick
                )
            }
            if (index < recipes.size) {
                Divider()
            }
        }
    }
}