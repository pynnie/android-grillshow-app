package de.shecken.grillshow.repository.recipe

import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    /**
     * List of all categories with 10 latest recipes
     */
    val categories: Flow<List<Category>>

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

    /**
     * Update a specific recipe
     *
     * @param recipeToUpdate the [Recipe] instance to be updated
     */
    suspend fun updateRecipe(recipeToUpdate: Recipe)

    /**
     * Get specified recipe from database
     *
     * @param id of the recipe
     */
    suspend fun getRecipeForId(id: String): Recipe?

    /**
     * Get all recipes that are marked as favorites
     */
    suspend fun getAllFavorites(): Flow<List<Recipe>>

    /**
     * Search for recipes
     *
     * @param query the search query
     */
    suspend fun searchRecipes(query: String): Flow<List<Recipe>>
}