package de.shecken.grillshow.shop.interactor

import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    /**
     * Search for recipes
     *
     * @param query the search query
     */
    suspend fun searchForRecipes(query: String): Flow<List<Recipe>>

    /**
     * Update the favorite property of a recipe
     *
     * @param recipeId the id of the recipe
     * @param isFavorite the new favorite property
     */
    suspend fun updateFavoriteProperty(recipeId: String, isFavorite: Boolean)
}