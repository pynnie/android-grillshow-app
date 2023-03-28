package de.shecken.grillshow.repository.recipe

import de.shecken.grillshow.database.recipe.RecipeDao
import de.shecken.grillshow.database.recipe.RecipeEntity
import de.shecken.grillshow.networking.youtube.response.PlaylistItem
import de.shecken.grillshow.networking.youtube.YoutubeDataApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Instant

class RecipeRepositoryImpl(
    private val api: YoutubeDataApi,
    private val recipeDao: RecipeDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipeRepository {

    override val recipes: Flow<List<Recipe>> = recipeDao.getAll().map {
        it.map { entity ->
            Recipe(
                title = entity.title,
                description = entity.description,
                thumbnailUrl = entity.thumbnailUrl
            )
        }
    }

    override suspend fun fetchRecipes(pageToken: String, latestUploadDateString: String?) {
        withContext(dispatcher) {
            val response = api.requestLatestUploads(pageToken = pageToken)
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
                fetchRecipes(pageToken = token, latestUploadDateString = latestUploadDateString)
            }
        }
    }

    override suspend fun fetchLatestRecipes() =
        fetchRecipes(latestUploadDateString = recipeDao.getLatestUploadDate())

    override suspend fun fetchCategories() = withContext(dispatcher) {
        api.requestAllPlaylistsFromChannel()
        return@withContext
    }

    private fun isRecipe(videoTitle: String) =
        videoTitle.contains(RECIPE_TITLE_REGEX, ignoreCase = true)

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
            videoId = contentDetails.videoId,
            thumbnailUrl = snippet.thumbnails.default.url,
            isFavorite = false,
            uploadedAt = contentDetails.videoPublishedAt
        )

    companion object {
        private const val RECIPE_TITLE_REGEX = "die grillshow"
    }
}
