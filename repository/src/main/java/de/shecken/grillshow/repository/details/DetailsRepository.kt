package de.shecken.grillshow.repository.details

import de.shecken.grillshow.repository.recipe.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    /**
     * Get details for specific recipe
     *
     * @param id of the recipe
     * @return [Flow] of the requested recipe
     */
    fun recipeDetailsById(id: String): Flow<RecipeDetails?>
}