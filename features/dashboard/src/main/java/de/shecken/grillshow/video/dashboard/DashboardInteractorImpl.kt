package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

internal class DashboardInteractorImpl(private val recipeRepository: RecipeRepository) :
    DashboardInteractor {

    override suspend fun getCategoriesWithRecipes(): Flow<List<Category>> =
        recipeRepository.categories

    override suspend fun updateRecipe(recipe: Recipe) = recipeRepository.updateRecipe(recipe)
}
