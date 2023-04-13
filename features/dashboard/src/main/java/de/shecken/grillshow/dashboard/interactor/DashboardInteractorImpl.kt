package de.shecken.grillshow.dashboard.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Category
import kotlinx.coroutines.flow.Flow

internal class DashboardInteractorImpl(private val recipeRepository: RecipeRepository) :
    DashboardInteractor {

    override fun getCategoriesWithRecipes(): Flow<List<Category>> =
        recipeRepository.categories

    override suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(id)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }
}
