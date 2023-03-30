package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.Category

internal sealed class DashboardSceenState {

    object Loading : DashboardSceenState()

    data class Success(val categories: List<Category>) : DashboardSceenState()

    object Failure : DashboardSceenState()
}
