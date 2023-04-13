package de.shecken.grillshow.dashboard.interactor

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
    fun getCategoriesWithRecipes(): Flow<List<Category>>

    /**
     * Update the favorite flag of a specified recipe
     *
     * @param id of the recipe to update
     * @param isFavorite flag for the new favorite status
     */
    suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean)
}
