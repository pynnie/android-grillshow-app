package de.shecken.grillshow.dashboard.interactor

import de.shecken.grillshow.repository.preferences.PreferencesRepository
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.toCategoryVo
import de.shecken.grillshow.toSearchResultVo
import de.shecken.grillshow.vo.CategoryVo
import de.shecken.grillshow.vo.SearchResultVo
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
            categories.map { category -> category.toCategoryVo() }
        }

    override fun searchForRecipes(query: String): Flow<List<SearchResultVo>> =
        recipeRepository.searchRecipes(query).map { recipes ->
            recipes.map { recipe -> recipe.toSearchResultVo() }
        }

    override suspend fun reloadRecipes() {
        with(recipeRepository) {
            clearRecipes()
            clearCategories()
            fetchAllRecipes()
            fetchCategories()
        }

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
