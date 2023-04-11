package de.shecken.grillshow.shop.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

class SearchInteractorImpl(private val recipeRepository: RecipeRepository) : SearchInteractor {

    override suspend fun searchForRecipes(query: String): Flow<List<Recipe>> =
        recipeRepository.searchRecipes(query)

    override suspend fun updateFavoriteProperty(recipeId: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(recipeId)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }
}
