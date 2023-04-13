package de.shecken.grillshow.details.interactor

import de.shecken.grillshow.details.vo.RecipeDetailsVo
import kotlinx.coroutines.flow.Flow

interface DetailsInteractor {

    /**
     * Get details for specific recipe from repository
     *
     * @param id of the recipe
     * @return [Flow] of the requested recipe
     */
    fun getRecipeDetails(id: String): Flow<RecipeDetailsVo?>

    /**
     * Update the favorite flag of a specified recipe
     *
     * @param id of the recipe to update
     * @param isFavorite flag for the new favorite status
     */
    suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean)
}