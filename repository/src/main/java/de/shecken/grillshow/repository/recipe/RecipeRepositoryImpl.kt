package de.shecken.grillshow.repository.recipe

import de.shecken.grillshow.database.category.CategoryDao
import de.shecken.grillshow.database.category.CategoryEntity
import de.shecken.grillshow.database.recipe.RecipeDao
import de.shecken.grillshow.networking.youtube.YoutubeDataApi
import de.shecken.grillshow.repository.*
import de.shecken.grillshow.repository.preferences.PreferencesRepository
import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.shared.provider.StringProvider
import de.shecken.networking.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.time.Instant

class RecipeRepositoryImpl(
    private val api: YoutubeDataApi,
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao,
    private val preferencesRepository: PreferencesRepository,
    private val stringProvider: StringProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipeRepository {

    private val channelCategories = categoryDao.loadCategoriesAndRecipes().map { theMap ->
        theMap.map { entry ->
            entry.key.toCategory(entry.value.map { entity -> entity.toRecipe() })
        }
    }

    private val uploadsCategoryId = BuildConfig.GRILLSHOW_UPLOADS_PLAYLIST_ID

    override val categories = combine(
        loadLatestCategory(LATEST_RECIPES_DASHBOARD_LIMIT),
        channelCategories
    ) { latest, cats ->
        return@combine cats.toMutableList().also { list ->
            if (list.isNotEmpty()) {
                list[0] = latest
            }
        }
    }

    override suspend fun fetchAllRecipes() = withContext(dispatcher) {
        fetchRecipes(playlistId = BuildConfig.GRILLSHOW_UPLOADS_PLAYLIST_ID)
        preferencesRepository.updateInitCompleted(true)
    }

    override suspend fun fetchLatestRecipes() =
        fetchRecipes(
            playlistId = uploadsCategoryId,
            latestUploadDateString = recipeDao.getLatestUploadDate()
        )

    override suspend fun fetchCategories() = withContext(dispatcher) {
        try {
            api.requestAllPlaylistsFromChannel().items.forEach { playListItem ->
                categoryDao.insert(playListItem.toCategoryEntity())
            }
            mapCategoriesToRecipes()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun getRecipeForId(id: String) = withContext(dispatcher) {
        recipeDao.getRecipeById(id)?.toRecipe()
    }

    override fun getRecipeForIdAsFlow(id: String): Flow<Recipe?> = recipeDao.getRecipeFlowById(id)
        .map { entity -> entity?.toRecipe() }

    override suspend fun updateRecipe(recipeToUpdate: Recipe) = withContext(dispatcher) {
        recipeDao.getRecipeById(recipeToUpdate.id)?.let { currentEntity ->
            recipeDao.update(
                recipeToUpdate.toEntity(
                    uploadedAt = currentEntity.uploadedAt,
                    categoryId = currentEntity.categoryId
                )
            )
        }
        return@withContext
    }

    override fun getAllFavorites(): Flow<List<Recipe>> = recipeDao.getAllFavorites()
        .map { list -> list.map { entity -> entity.toRecipe() } }

    override fun searchRecipes(query: String): Flow<List<Recipe>> =
        recipeDao.searchRecipes(query)
            .map { list -> list.map { entity -> entity.toRecipe() } }

    override fun getCategoryById(categoryId: String): Flow<Category?> =
        if (categoryId == uploadsCategoryId) {
            loadLatestCategory(limit = LATEST_RECIPES_CATEGORY_LIMIT)
        } else {
            loadCategoryById(categoryId)
        }

    override suspend fun clearCategories() = withContext(dispatcher) {
        categoryDao.clearAll()
    }

    override suspend fun clearRecipes() = withContext(dispatcher) {
        recipeDao.clearAll()
    }

    private fun loadCategoryById(categoryId: String) = combine(
        categoryDao.getCategoryByIdAsFlow(categoryId),
        recipeDao.getRecipesForCategory(categoryId)
    ) { category, recipes ->
        category?.let { notNullCategory ->
            Category(
                id = categoryId,
                title = notNullCategory.title,
                description = notNullCategory.description,
                recipes = recipes.map { it.toRecipe() })
        }
    }

    private fun loadLatestCategory(limit: Int) =
        recipeDao.getLatestRecipes(limit = limit).map { recipes ->
            Category(
                id = uploadsCategoryId,
                title = stringProvider.provideString(R.string.dashboard_latest_recipes),
                description = "",
                recipes = recipes.map { it.toRecipe() }
            )
        }


    private suspend fun fetchRecipes(
        playlistId: String,
        pageToken: String = "",
        latestUploadDateString: String? = null
    ) {
        withContext(dispatcher) {
            try {
                val response = api.requestPlaylistItems(
                    pageToken = pageToken,
                    playlistId = playlistId
                )
                response.items.forEach { responseItem ->
                    if (isNew(
                            latestUploadDateString,
                            responseItem.contentDetails.videoPublishedAt
                        )
                    ) {
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
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext
            }
        }
    }

    private suspend fun mapCategoriesToRecipes() {
        categoryDao.getAllCategories().forEach { category ->
            updateRecipesForCategory(category)
        }
    }

    private suspend fun updateRecipesForCategory(category: CategoryEntity, pageToken: String = "") {
        try {
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
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

    private fun isRecipe(videoTitle: String) =
        videoTitle.contains(RECIPE_TITLE_REGEX, ignoreCase = true) &&
                !videoTitle.contains(RECIPE_SHORTS_REGEX, ignoreCase = true) &&
                !videoTitle.contains(RECIPE_SPECIAL_REGEX, ignoreCase = true)

    private fun isNew(latestUploadDateString: String?, itemUploadDateString: String) =
        latestUploadDateString?.let {
            val latestUploadDate = Instant.parse(latestUploadDateString)
            val currentUploadDate = Instant.parse(itemUploadDateString)
            return currentUploadDate.isAfter(latestUploadDate)
        } ?: true

    companion object {
        private const val RECIPE_TITLE_REGEX = "die grillshow"
        private const val RECIPE_SHORTS_REGEX = "die grillshow shorts"
        private const val RECIPE_SPECIAL_REGEX = "die grillshow special"

        private const val LATEST_RECIPES_CATEGORY_LIMIT = 50
        private const val LATEST_RECIPES_DASHBOARD_LIMIT = 10
    }
}

