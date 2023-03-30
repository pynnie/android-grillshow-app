package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.Category
import de.shecken.grillshow.repository.recipe.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Interactor for the [DashboardScreen]
 */
internal interface DashboardInteractor {

    /**
     * @return flow of all [Category]s with the 10 latest [Recipe]s as a [Flow]
     */
    suspend fun getCategoriesWithRecipes(): Flow<List<Category>>
}
