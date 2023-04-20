package de.shecken.grillshow.dashboard.interactor

import de.shecken.grillshow.vo.CategoryVo
import de.shecken.grillshow.vo.SearchResultVo
import kotlinx.coroutines.flow.Flow

/**
 * Interactor for the [DashboardScreen]
 */
internal interface DashboardInteractor {

    /**
     * flow of the app initialization state
     */
    val isAppInitialized: Flow<Boolean>

    /**
     * @return flow of all [CategoryVo]s with the 10 latest [RecipeListItemVo]s as a [Flow]
     */
    fun getCategoriesWithRecipes(): Flow<List<CategoryVo>>

    /**
     * Update the favorite flag of a specified recipe
     *
     * @param id of the recipe to update
     * @param isFavorite flag for the new favorite status
     */
    suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean)

    /**
     * Search for recipes
     *
     * @param query the search query
     */
    fun searchForRecipes(query: String): Flow<List<SearchResultVo>>

    /**
     * Update the app initialization state
     *
     * @param newState the new state
     */
    suspend fun updateAppInitializedState(newState: Boolean)

    /**
     * Clear all recipes from the database and reload them from the network
     */
    suspend fun reloadRecipes()
}
