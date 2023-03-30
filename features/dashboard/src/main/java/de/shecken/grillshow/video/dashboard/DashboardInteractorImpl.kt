package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.recipe.Category
import de.shecken.grillshow.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow

internal class DashboardInteractorImpl(private val recipeRepository: RecipeRepository) :
    DashboardInteractor {

    override suspend fun getCategoriesWithRecipes(): Flow<List<Category>> =
        recipeRepository.categories
}
