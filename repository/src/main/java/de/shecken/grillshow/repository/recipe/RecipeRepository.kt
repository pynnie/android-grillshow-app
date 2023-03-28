package de.shecken.grillshow.repository.recipe

import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    val recipes: Flow<List<Recipe>>

    /**
     * Fetch all recipes from API
     */
    suspend fun fetchAllRecipes()

    /**
     * Only fetch the latest recipes missing in the database
     */
    suspend fun fetchLatestRecipes()

    /**
     * Fetches all playlists from API and maps them to categories
     */
    suspend fun fetchCategories()
}