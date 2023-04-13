package de.shecken.grillshow.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.dashboard.navigation.DashboardRouter
import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.dashboard.ui.DashboardSceenState.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class DashboardViewModel(
    private val dashboardRouter: DashboardRouter,
    private val interactor: DashboardInteractor
) : ViewModel() {

    private val _dashboardScreenState = loadRecipes()
    val dashboardScreenState =
        _dashboardScreenState.stateIn(viewModelScope, SharingStarted.Eagerly, Loading)

    private fun loadRecipes(): Flow<DashboardSceenState> =
        interactor
            .getCategoriesWithRecipes()
            .map { categoryList ->
                if (categoryList.isEmpty()) {
                    Failure
                } else {
                    Success(
                        categories = categoryList,
                        onFavIconClick = ::onFavIconClick,
                        onRecipeClick = ::onRecipeClick
                    )
                }
            }

    fun onFavIconClick(id: String, isFavorite: Boolean) = viewModelScope.launch {
        interactor.updateFavoriteProperty(id = id, isFavorite = isFavorite)
    }

    fun onRecipeClick(recipe: Recipe) = dashboardRouter.openRecipeDetails(recipe.id)
}
