package de.shecken.grillshow.shop.ui

import de.shecken.grillshow.repository.recipe.model.Recipe

sealed class SearchScreenState {

    object Loading : SearchScreenState()

    data class Success(
        val recipes: List<Recipe>,
        val onFavIconClick: (String, Boolean) -> Unit,
        val onRecipeClick: (Recipe) -> Unit
    ) : SearchScreenState()

    object Empty : SearchScreenState()
}