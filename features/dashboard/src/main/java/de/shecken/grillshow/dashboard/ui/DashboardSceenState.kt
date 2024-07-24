package de.shecken.grillshow.dashboard.ui

import de.shecken.grillshow.vo.CategoryVo
import de.shecken.grillshow.vo.SearchResultVo

internal sealed class DashboardSceenState {

    object Loading : DashboardSceenState()

    data class Success(
        val categories: List<CategoryVo>,
        val onFavIconClick: (String, Boolean) -> Unit,
        val onRecipeClick: (String) -> Unit,
        val onCategoryClick: (String) -> Unit
    ) : DashboardSceenState()

    data class Failure(
        val onReloadClick: () -> Unit
    ) : DashboardSceenState()

    sealed class SearchScreenState(val query: String) : DashboardSceenState() {
        data class Success(
            val recipes: List<SearchResultVo>,
            val currentQuery: String,
            val onRecipeClick: (String) -> Unit
        ) : SearchScreenState(currentQuery)

        class Empty(currentQuery: String) : SearchScreenState(currentQuery)
    }
}
