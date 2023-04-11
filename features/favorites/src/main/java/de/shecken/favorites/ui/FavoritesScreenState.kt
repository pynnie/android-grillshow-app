package de.shecken.favorites.ui

import de.shecken.grillshow.repository.recipe.model.Recipe

sealed class FavoritesScreenState {

    object Loading : FavoritesScreenState()

    data class Success(
        val favoriteList: List<Recipe>,
        val onItemClick: (String) -> Unit
    ) : FavoritesScreenState()

    object Empty : FavoritesScreenState()
}