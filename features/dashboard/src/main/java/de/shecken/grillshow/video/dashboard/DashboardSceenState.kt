package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe

internal sealed class DashboardSceenState {

    object Loading : DashboardSceenState()

    data class Success(
        val categories: List<Category>,
        val onFavIconClick: (Recipe) -> Unit
    ) : DashboardSceenState()

    object Failure : DashboardSceenState()
}
