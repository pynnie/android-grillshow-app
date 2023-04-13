package de.shecken.grillshow.shop.ui

import de.shecken.grillshow.shop.vo.SearchResultVo

sealed class SearchScreenState {

    object Loading : SearchScreenState()

    data class Success(
        val recipes: List<SearchResultVo>,
        val onRecipeClick: (String) -> Unit
    ) : SearchScreenState()

    object Empty : SearchScreenState()
}