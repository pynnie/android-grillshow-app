package de.shecken.grillshow.dashboard.interactor

import de.shecken.grillshow.dashboard.vo.CategoryVo
import de.shecken.grillshow.dashboard.vo.RecipeListItemVo
import de.shecken.grillshow.dashboard.vo.SearchResultVo
import de.shecken.grillshow.repository.preferences.PreferencesRepository
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DashboardInteractorImpl(
    private val recipeRepository: RecipeRepository,
    private val prefsRepository: PreferencesRepository
) :
    DashboardInteractor {

    override val isAppInitialized: Flow<Boolean>
        get() = prefsRepository.appPreferencesFlow.map { prefs -> prefs.isInitComplete }

    override fun getCategoriesWithRecipes(): Flow<List<CategoryVo>> =
        recipeRepository.categories.map { categories ->
            categories.map { category ->
                mapCategoryToCategoryVo(category)
            }
        }

    override fun searchForRecipes(query: String): Flow<List<SearchResultVo>> =
        recipeRepository.searchRecipes(query).map { recipes ->
            recipes.map { recipe ->
                SearchResultVo(
                    id = recipe.id,
                    title = recipe.title,
                    imageUrl = recipe.thumbnailUrl
                )
            }
        }

    override suspend fun reloadRecipes() {
        with(recipeRepository) {
            clearRecipes()
            clearCategories()
            fetchAllRecipes()
            fetchCategories()
        }

    }

    private fun mapCategoryToCategoryVo(category: Category) = with(category) {
        CategoryVo(
            id = id,
            title = title,
            recipes = recipes.map { recipe ->
                RecipeListItemVo(
                    id = recipe.id,
                    title = recipe.title,
                    imageUrl = recipe.thumbnailUrl,
                    isFavorite = recipe.isFavorite
                )
            }
        )
    }

    override suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(id)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }

    override suspend fun updateAppInitializedState(newState: Boolean) {
        prefsRepository.updateInitCompleted(newState)
    }
}
