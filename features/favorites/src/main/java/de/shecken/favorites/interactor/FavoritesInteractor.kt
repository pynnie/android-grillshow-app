package de.shecken.favorites.interactor

import de.shecken.favorites.vo.FavoriteVo
import kotlinx.coroutines.flow.Flow

interface FavoritesInteractor {

    /**
     * Get all recipes marked as favorite from repository
     */
    fun getFavoriteRecipes(): Flow<List<FavoriteVo>>
}