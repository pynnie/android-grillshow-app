package de.shecken.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.favorites.interactor.FavoritesInteractor
import de.shecken.favorites.navigation.FavoritesRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRouter: FavoritesRouter,
    private val interactor: FavoritesInteractor
) : ViewModel() {

    private val _favoritesScreenState =
        MutableStateFlow<FavoritesScreenState>(FavoritesScreenState.Loading)
    val favoritesScreenState: StateFlow<FavoritesScreenState> = _favoritesScreenState

    init {
        loadFavorites()
    }

    fun onItemClick(recipeId: String) = favoritesRouter.goToRecipeDetails(recipeId)

    private fun loadFavorites() {
        viewModelScope.launch {
            interactor.getFavoriteRecipes().collect { favList ->
                _favoritesScreenState.update {
                    if (favList.isEmpty()) {
                        FavoritesScreenState.Empty
                    } else {
                        FavoritesScreenState.Success(
                            favoriteList = favList,
                            onItemClick = ::onItemClick
                        )
                    }
                }
            }
        }
    }
}