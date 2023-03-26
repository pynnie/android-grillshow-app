package de.shecken.grillshow.repository.recipe

import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    val recipes: Flow<List<Recipe>>

    /**
     * Fetch recipes from API
     */
    suspend fun fetchRecipes()
}