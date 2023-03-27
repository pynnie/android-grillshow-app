package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.Recipe
import de.shecken.grillshow.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow

internal class DashboardInteractorImpl(private val recipeRepository: RecipeRepository) :
    DashboardInteractor {
    override suspend fun getRecipes(): Flow<List<Recipe>> =
        recipeRepository.recipes.also { recipeRepository.fetchRecipes() }
}
