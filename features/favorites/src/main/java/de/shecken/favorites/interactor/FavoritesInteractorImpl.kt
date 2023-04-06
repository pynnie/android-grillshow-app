package de.shecken.favorites.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.model.Recipe
import kotlinx.coroutines.flow.Flow

class FavoritesInteractorImpl(private val recipeRepository: RecipeRepository) :
    FavoritesInteractor {

    override suspend fun getFavoriteRecipes(): Flow<List<Recipe>> =
        recipeRepository.getAllFavorites()
}