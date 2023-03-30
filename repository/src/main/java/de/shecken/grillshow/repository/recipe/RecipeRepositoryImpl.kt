package de.shecken.grillshow.repository.recipe

import de.shecken.grillshow.database.category.CategoryDao
import de.shecken.grillshow.database.category.CategoryEntity
import de.shecken.grillshow.database.recipe.RecipeDao
import de.shecken.grillshow.database.recipe.RecipeEntity
import de.shecken.grillshow.networking.youtube.Playlist
import de.shecken.grillshow.networking.youtube.YoutubeDataApi
import de.shecken.grillshow.networking.youtube.response.PlaylistItem
import de.shecken.networking.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Instant

class RecipeRepositoryImpl(
    private val api: YoutubeDataApi,
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipeRepository {

    override val recipes: Flow<List<Recipe>> = recipeDao.getLatestRecipes().map { list ->
        list.map { entity -> entity.toRecipe() }
    }

    override suspend fun fetchAllRecipes() =
        fetchRecipes(playlistId = BuildConfig.GRILLSHOW_UPLOADS_PLAYLIST_ID)

    override suspend fun fetchLatestRecipes() =
        fetchRecipes(
            playlistId = BuildConfig.GRILLSHOW_UPLOADS_PLAYLIST_ID,
            latestUploadDateString = recipeDao.getLatestUploadDate()
        )

    override suspend fun fetchCategories() = withContext(dispatcher) {
        api.requestAllPlaylistsFromChannel().items.forEach { playListItem ->
            categoryDao.insert(playListItem.toCategoryEntity())
        }
        mapCategoriesToRecipes()
    }

    private suspend fun fetchRecipes(
        playlistId: String,
        pageToken: String = "",
        latestUploadDateString: String? = null
    ) {
        withContext(dispatcher) {
            val response = api.requestPlaylistItems(
                pageToken = pageToken,
                playlistId = playlistId
            )
            response.items.forEach { responseItem ->
                if (isNew(latestUploadDateString, responseItem.contentDetails.videoPublishedAt)) {
                    if (isRecipe(responseItem.snippet.title)) {
                        recipeDao.insert(responseItem.toRecipeEntity())
                    }
                } else {
                    return@withContext
                }
            }
            response.nextPageToken?.let { token ->
                fetchRecipes(
                    pageToken = token,
                    latestUploadDateString = latestUploadDateString,
                    playlistId = playlistId
                )
            }
        }
    }

    private suspend fun mapCategoriesToRecipes() {
        categoryDao.getAllCategories().forEach { category ->
            updateRecipesForCategory(category)
        }
    }

    private suspend fun updateRecipesForCategory(category: CategoryEntity, pageToken: String = "") {
        val playlistResponse =
            api.requestPlaylistItems(playlistId = category.id, pageToken = pageToken)
        playlistResponse.items.forEach { item ->
            recipeDao.getRecipeById(item.contentDetails.videoId)?.let { recipeToUpdate ->
                recipeDao.insert(recipeToUpdate.copy(categoryId = category.id))
            }
        }
        playlistResponse.nextPageToken?.let { token ->
            updateRecipesForCategory(category = category, pageToken = token)
        }
    }

    private fun isRecipe(videoTitle: String) =
        videoTitle.contains(RECIPE_TITLE_REGEX, ignoreCase = true) &&
                !videoTitle.contains(RECIPE_SHORTS_REGEX, ignoreCase = true)

    private fun isNew(latestUploadDateString: String?, itemUploadDateString: String) =
        latestUploadDateString?.let {
            val latestUploadDate = Instant.parse(latestUploadDateString)
            val currentUploadDate = Instant.parse(itemUploadDateString)
            return currentUploadDate.isAfter(latestUploadDate)
        } ?: true

    private fun PlaylistItem.toRecipeEntity() =
        RecipeEntity(
            id = contentDetails.videoId,
            title = snippet.title,
            description = snippet.description,
            thumbnailUrl = snippet.thumbnails.high?.url ?: snippet.thumbnails.medium?.url
            ?: snippet.thumbnails.standard?.url ?: snippet.thumbnails.default?.url ?: "",
            isFavorite = false,
            uploadedAt = contentDetails.videoPublishedAt
        )

    private fun Playlist.toCategoryEntity() =
        CategoryEntity(
            id = id,
            title = snippet.title,
            description = snippet.description
        )

    private fun RecipeEntity.toRecipe() = Recipe(
        id = id,
        title = title,
        description = description,
        thumbnailUrl = thumbnailUrl
    )

    companion object {
        private const val RECIPE_TITLE_REGEX = "die grillshow"
        private const val RECIPE_SHORTS_REGEX = "die grillshow shorts"
    }
}
