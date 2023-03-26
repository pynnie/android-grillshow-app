package de.shecken.grillshow.repository.recipe

import de.shecken.grillshow.database.recipe.RecipeDao
import de.shecken.grillshow.database.recipe.RecipeEntity
import de.shecken.grillshow.networking.youtube.YoutubeDataApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

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

    override suspend fun fetchRecipes() {
        withContext(dispatcher) {
            api.requestLatestUploads().items.map { responseItem ->
                Timber.w(responseItem.snippet.title)
                val databaseItem = RecipeEntity(
                    id = responseItem.contentDetails.videoId,
                    title = responseItem.snippet.title,
                    description = responseItem.snippet.description,
                    videoId = responseItem.contentDetails.videoId,
                    thumbnailUrl = responseItem.snippet.thumbnails.default.url,
                    isFavorite = false,
                    uploadedAt = 0
                )
                recipeDao.insert(databaseItem)
            }
        }
    }
}
