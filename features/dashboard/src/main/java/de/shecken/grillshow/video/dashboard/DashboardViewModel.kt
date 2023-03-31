package de.shecken.grillshow.video.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.video.DashboardRouter
import de.shecken.grillshow.video.dashboard.DashboardSceenState.*
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
                            Success(categories = categoryList, onFavIconClick = ::onFavIconClick)
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
}
