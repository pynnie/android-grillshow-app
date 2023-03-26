package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.Recipe

internal sealed class DashboardSceenState {

    object Loading : DashboardSceenState()

    data class Success(val recipes: List<Recipe>) : DashboardSceenState()

    object Failure : DashboardSceenState()
}
