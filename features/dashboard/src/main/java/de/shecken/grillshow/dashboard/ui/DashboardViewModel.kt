package de.shecken.grillshow.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.DashboardRouter
import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.dashboard.ui.DashboardSceenState.*
import de.shecken.grillshow.dashboard.vo.SearchResultVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
internal class DashboardViewModel(
    private val dashboardRouter: DashboardRouter,
    private val interactor: DashboardInteractor
) : ViewModel() {

    private val _dashboardScreenState = getScreenState()

    private val _searchQuery = MutableStateFlow("")
    private val _searchScreenState = _searchQuery.flatMapLatest { query ->
        updateSearchResults(query)
    }

    private var isSearchActive = MutableStateFlow(false)

    private val _screenState = combine(
        _dashboardScreenState,
        _searchScreenState,
        isSearchActive
    ) { dashboardState, searchState, isSearchMode ->
        if (isSearchMode) {
            searchState
        } else {
            dashboardState
        }
    }
    val screenState: StateFlow<DashboardSceenState> =
        _screenState.stateIn(viewModelScope, SharingStarted.Eagerly, Loading)

    fun onQueryChange(query: String) {
        _searchQuery.update { query }
    }

    fun toggleSearchMode(isActive: Boolean) {
        isSearchActive.update { isActive }
    }

    private fun updateSearchResults(query: String) =
        if (query.isNotEmpty()) {
            interactor.searchForRecipes(query).map { resultList ->
                handleSearchResult(resultList)
            }
        } else {
            flowOf(SearchScreenState.Empty(query))
        }


    private fun getScreenState(): Flow<DashboardSceenState> =
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

    private fun handleSearchResult(recipeList: List<SearchResultVo>) = if (recipeList.isEmpty()) {
        SearchScreenState.Empty(_searchQuery.value)
    } else {
        SearchScreenState.Success(
            recipes = recipeList, currentQuery = _searchQuery.value, onRecipeClick = ::onRecipeClick
        )
    }

    fun onFavIconClick(id: String, isFavorite: Boolean) = viewModelScope.launch {
        interactor.updateFavoriteProperty(id = id, isFavorite = isFavorite)
    }

    fun onRecipeClick(recipeId: String) = dashboardRouter.openRecipeDetails(recipeId)
}
