package de.shecken.grillshow.shop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.shop.navigation.SearchRouter
import de.shecken.grillshow.shop.interactor.SearchInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val interactor: SearchInteractor, private val router: SearchRouter
) : ViewModel() {

    private val _searchScreenState = MutableStateFlow<SearchScreenState>(SearchScreenState.Empty)
    val searchScreenState: StateFlow<SearchScreenState> = _searchScreenState


    fun onQueryChange(query: String) {
        _searchScreenState.update { SearchScreenState.Loading }
        viewModelScope.launch {
            interactor.searchForRecipes(query).collect { recipeList ->
                handleSearchResult(recipeList)
            }
        }
    }

    private fun handleSearchResult(recipeList: List<Recipe>) {
        if (recipeList.isEmpty()) {
            _searchScreenState.update { SearchScreenState.Empty }
        } else {
            _searchScreenState.update {
                SearchScreenState.Success(
                    recipes = recipeList,
                    onFavIconClick = ::onFavIconClick,
                    onRecipeClick = ::onRecipeClick
                )
            }
        }
    }

    private fun onRecipeClick(recipe: Recipe) = router.openRecipeDetails(recipeId = recipe.id)


    private fun onFavIconClick(recipeId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            interactor.updateFavoriteProperty(recipeId = recipeId, isFavorite = isFavorite)
        }
    }


}
