@file:OptIn(ExperimentalMaterial3Api::class)

package de.shecken.grillshow.details.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import de.shecken.grillshow.dashboard.R
import de.shecken.grillshow.vo.RecipeDetailsVo
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.ui.BackButton
import de.shecken.grillshow.shared.ui.Message
import de.shecken.grillshow.shared.ui.FavIconButton
import de.shecken.grillshow.shared.ui.FullScreenLoadingIndicator
import org.koin.androidx.compose.getViewModel

@Composable
internal fun DetailsScreen(viewModel: DetailsViewModel = getViewModel()) {

    val state: DetailsScreenState by viewModel.detailsScreenState.collectAsStateWithLifecycle()

    DetailsScreen(
        state = state,
        onBackButtonClick = viewModel::onBackButtonClick,
        onShareIconClick = viewModel::onShareIconClick
    )
}

@Composable
private fun DetailsScreen(
    state: DetailsScreenState,
    onBackButtonClick: () -> Unit,
    onShareIconClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                state = state,
                onBackButtonClick = onBackButtonClick,
                onShareIconClick = onShareIconClick
            )
        }) { padding ->
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
            is DetailsScreenState.Success -> HandleSuccessState(
                recipeDetails = state.recipeDetails,
                onVideoButtonClick = state.onVideoButtonClick
            )
            is DetailsScreenState.Failure -> Failure(state.onBackButtonClick)
        }
    }
}

@Composable
private fun DetailsTopBar(
    state: DetailsScreenState,
    onBackButtonClick: () -> Unit,
    onShareIconClick: (String) -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.details_title)) },
        navigationIcon = {
            BackButton(onClick = onBackButtonClick)
        },
        actions = {
            if (state is DetailsScreenState.Success) {
                FavIconButton(
                    recipeId = state.recipeDetails.id,
                    isFavorite = state.recipeDetails.isFavorite,
                    onClick = state.onFavIconClick
                )

                IconButton(onClick = { onShareIconClick(state.recipeDetails.id) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

@Composable
private fun Failure(onBackButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Message(
            title = stringResource(id = R.string.details_error_title),
            message = stringResource(id = R.string.details_error_message),
            buttonText = stringResource(id = R.string.details_error_button),
            imageRes = R.drawable.ninja,
            onButtonClick = onBackButtonClick
        )
    }
}

@Composable
private fun HandleSuccessState(recipeDetails: RecipeDetailsVo, onVideoButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        YoutubeVideo(videoId = recipeDetails.id, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        // recipe title
        Text(text = recipeDetails.title, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(40.dp))

        if (recipeDetails.ingredientlist.isEmpty()) {
            EmptyIngredientList(onVideoButtonClick = onVideoButtonClick)
            Spacer(modifier = Modifier.height(24.dp))
        } else {
            // subtitle for list
            Text(
                text = stringResource(id = R.string.details_list_title),
                style = MaterialTheme.typography.titleSmall
            )
            IngredientList(ingredients = recipeDetails.ingredientlist)
        }
    }
}

@Composable
fun YoutubeVideo(
    videoId: String,
    modifier: Modifier
) {
    val startVideoAtSecond = 0f
    AndroidView(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        factory = { context ->
            val view = YouTubePlayerView(context).apply {
                addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.cueVideo(
                                videoId = videoId,
                                startSeconds = startVideoAtSecond
                            )
                        }
                    }
                )
            }
            return@AndroidView view
        })
}

@Composable
private fun IngredientList(ingredients: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        LazyColumn {
            itemsIndexed(ingredients) { index, ingredient ->
                IngredientItem(item = ingredient)
                if (index < ingredients.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyIngredientList(onVideoButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
    ) {
        Message(
            title = stringResource(id = R.string.details_no_ingredients_title),
            message = stringResource(id = R.string.details_no_ingredients_message),
            buttonText = stringResource(id = R.string.details_no_ingredients_button),
            imageRes = R.drawable.johnny,
            onButtonClick = onVideoButtonClick
        )
    }
}

@Composable
private fun IngredientItem(item: String) {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        text = item,
        style = MaterialTheme.typography.bodyMedium
    )
}

private val previewIngredients = listOf("1 große Salami", "Öl", "Salz", "Dose Kidneybohnen")

@Composable
@Preview
private fun IngredientItemPreview() {
    GrillshowTheme {
        IngredientList(ingredients = previewIngredients)
    }
}

@Composable
@Preview
private fun TopBarPreview() {
    GrillshowTheme {
        DetailsTopBar(
            DetailsScreenState.Success(
                recipeDetails = RecipeDetailsVo(
                    "id",
                    "Test Title",
                    previewIngredients,
                    false
                ),
                onFavIconClick = { _, _ -> },
                onVideoButtonClick = {}
            ),
            onBackButtonClick = {},
            onShareIconClick = {}
        )
    }
}