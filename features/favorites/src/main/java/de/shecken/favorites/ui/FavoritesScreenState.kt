package de.shecken.favorites.ui

import de.shecken.favorites.vo.FavoriteVo

sealed class FavoritesScreenState {

    object Loading : FavoritesScreenState()

    data class Success(
        val favoriteList: List<FavoriteVo>,
        val onItemClick: (String) -> Unit
    ) : FavoritesScreenState()

    data class Empty(val onDashboardButtonClick: () -> Unit) : FavoritesScreenState()
}