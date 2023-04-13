package de.shecken.grillshow.dashboard.ui

import de.shecken.grillshow.dashboard.vo.CategoryVo
import de.shecken.grillshow.dashboard.vo.RecipeListItemVo

internal sealed class DashboardSceenState {

    object Loading : DashboardSceenState()

    data class Success(
        val categories: List<CategoryVo>,
        val onFavIconClick: (String, Boolean) -> Unit,
        val onRecipeClick: (RecipeListItemVo) -> Unit
    ) : DashboardSceenState()

    object Failure : DashboardSceenState()
}
