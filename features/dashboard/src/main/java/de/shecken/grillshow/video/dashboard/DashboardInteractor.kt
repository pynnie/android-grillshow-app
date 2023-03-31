package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Interactor for the [DashboardScreen]
 */
internal interface DashboardInteractor {

    /**
     * @return flow of all [Category]s with the 10 latest [Recipe]s as a [Flow]
     */
    suspend fun getCategoriesWithRecipes(): Flow<List<Category>>

    /**
     * Update a specific [Recipe] in repository
     *
     * @param recipe the recipe to update
     */
    suspend fun updateRecipe(recipe: Recipe)
}
