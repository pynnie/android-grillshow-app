package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * Interactor for the [DashboardScreen]
 */
internal interface DashboardInteractor {

    /**
     * @return the 10 latest [Recipe]s as a [Flow]
     */
    suspend fun getRecipes(): Flow<List<Recipe>>
}
