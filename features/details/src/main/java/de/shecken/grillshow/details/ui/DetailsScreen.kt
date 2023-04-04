@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.details.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.shecken.grillshow.details.R
import de.shecken.grillshow.repository.recipe.model.RecipeDetails
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import org.koin.androidx.compose.getViewModel

@Composable
internal fun DetailsScreen(viewModel: DetailsViewModel = getViewModel()) {

    val state: DetailsScreenState by viewModel.detailsScreenState.collectAsState()

    DetailsScreen(state = state)
}

@Composable
private fun DetailsScreen(state: DetailsScreenState) {
    Scaffold(topBar = { DetailsTopBar() }) { padding ->
        HandleScreenState(modifier = Modifier.padding(padding), state = state)
    }
}

@Composable
private fun HandleScreenState(modifier: Modifier, state: DetailsScreenState) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (state) {
            is DetailsScreenState.Loading -> FullScreenLoadingIndicator()
            is DetailsScreenState.Success -> HandleSuccessState(state.recipeDetails)
            is DetailsScreenState.Failure -> Error()
        }
    }
}

@Composable
private fun DetailsTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.details_title)) })
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
private fun HandleSuccessState(recipeDetails: RecipeDetails) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = recipeDetails.title, style = MaterialTheme.typography.titleMedium)

        Text(
            text = stringResource(id = R.string.details_list_title),
            style = MaterialTheme.typography.titleSmall
        )

        LazyColumn {
            items(recipeDetails.ingredientlist) { ingredient ->
                ListItem(headlineText = { Text(text = ingredient) })
            }
        }
    }
}