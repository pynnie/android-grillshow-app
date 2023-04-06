package de.shecken.grillshow.details.interactor

import de.shecken.grillshow.repository.recipe.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface DetailsInteractor {

    /**
     * Get details for specific recipe from repository
     *
     * @param id of the recipe
     * @return [Flow] of the requested recipe
     */
    fun getRecipeDetails(id: String): Flow<RecipeDetails?>
}