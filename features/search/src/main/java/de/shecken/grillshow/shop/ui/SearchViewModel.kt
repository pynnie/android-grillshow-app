package de.shecken.grillshow.shop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.shop.navigation.SearchRouter
import de.shecken.grillshow.shop.interactor.SearchInteractor
import de.shecken.grillshow.shop.vo.SearchResultVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchViewModel(
    private val interactor: SearchInteractor, private val router: SearchRouter
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _searchScreenState = _searchQuery.flatMapLatest { query ->
        updateSearchResults(query)
    }

    val searchScreenState: StateFlow<SearchScreenState> =
        _searchScreenState.stateIn(viewModelScope, SharingStarted.Eagerly, SearchScreenState.Empty)

    fun onQueryChange(query: String) {
        _searchQuery.value = query
    }

    private fun updateSearchResults(query: String) =
        if (query.isNotEmpty()) {
            interactor.searchForRecipes(query).map { resultList ->
                handleSearchResult(resultList)
            }
        } else {
            flowOf(SearchScreenState.Empty)
        }

    private fun handleSearchResult(recipeList: List<SearchResultVo>) = if (recipeList.isEmpty()) {
        SearchScreenState.Empty
    } else {
        SearchScreenState.Success(
            recipes = recipeList, onRecipeClick = ::onRecipeClick
        )
    }

    private fun onRecipeClick(recipeId: String) =
        router.openRecipeDetails(recipeId = recipeId)
}
