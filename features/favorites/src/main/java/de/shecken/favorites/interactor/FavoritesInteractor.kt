package de.shecken.favorites.interactor

import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

interface FavoritesInteractor {

    /**
     * Get all recipes marked as favorite from repository
     */
    suspend fun getFavoriteRecipes(): Flow<List<Recipe>>
}