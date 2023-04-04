package de.shecken.grillshow.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.dashboard.navigation.DashboardRouter
import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.dashboard.ui.DashboardSceenState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

internal class DashboardViewModel(
    private val dashboardRouter: DashboardRouter,
    private val interactor: DashboardInteractor
) : ViewModel() {

    private val _dashboardScreenState = MutableStateFlow<DashboardSceenState>(Loading)
    val dashboardScreenState: StateFlow<DashboardSceenState> = _dashboardScreenState

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            try {
                interactor
                    .getCategoriesWithRecipes()
                    .collect { categoryList ->
                        _dashboardScreenState.value =
                            Success(
                                categories = categoryList,
                                onFavIconClick = ::onFavIconClick,
                                onRecipeClick = ::onRecipeClick
                            )
                    }
            } catch (t: Throwable) {
                _dashboardScreenState.value = Failure
                Timber.e(t)
            }
        }
    }

    fun onFavIconClick(recipe: Recipe) = viewModelScope.launch {
        interactor.updateRecipe(recipe.copy(isFavorite = !recipe.isFavorite))
    }

    fun onRecipeClick(recipe: Recipe) = dashboardRouter.openRecipeDetails(recipe.id)
}
