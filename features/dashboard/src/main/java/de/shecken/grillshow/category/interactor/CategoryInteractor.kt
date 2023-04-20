package de.shecken.grillshow.category.interactor

import de.shecken.grillshow.vo.CategoryVo
import kotlinx.coroutines.flow.Flow


interface CategoryInteractor {

    /**
     * Load specified cateogry from repository
     *
     * @param id of the category to load
     */
    fun loadCategory(id: String): Flow<CategoryVo?>

    /**
     * Update the favorite flag of a specified recipe
     *
     * @param id of the recipe to update
     * @param isFavorite flag for the new favorite status
     */
    suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean)

    /**
     * Update categories in repository
     */
    suspend fun updateCategories()
}