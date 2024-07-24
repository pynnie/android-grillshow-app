package de.shecken.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.favorites.interactor.FavoritesInteractor
import de.shecken.favorites.navigation.FavoritesRouter
import kotlinx.coroutines.flow.*

class FavoritesViewModel(
    private val favoritesRouter: FavoritesRouter,
    private val interactor: FavoritesInteractor
) : ViewModel() {

    private val _favoritesScreenState = loadFavorites()
    val favoritesScreenState: StateFlow<FavoritesScreenState> = _favoritesScreenState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        FavoritesScreenState.Loading
    )

    fun onItemClick(recipeId: String) = favoritesRouter.openRecipeDetails(recipeId)

    fun onDashboardButtonClick() = favoritesRouter.openDashboard()

    private fun loadFavorites() =
        interactor.getFavoriteRecipes().map { favList ->
            if (favList.isEmpty()) {
                FavoritesScreenState.Empty(::onDashboardButtonClick)
            } else {
                FavoritesScreenState.Success(
                    favoriteList = favList,
                    onItemClick = ::onItemClick
                )
            }
        }
}