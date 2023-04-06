package de.shecken.grillshow.details.interactor

import de.shecken.grillshow.repository.details.DetailsRepository
import de.shecken.grillshow.repository.recipe.RecipeRepository

class DetailsInteractorImpl(
    private val detailsRepository: DetailsRepository,
    private val recipeRepository: RecipeRepository
) :
    DetailsInteractor {

    override fun getRecipeDetails(id: String) = detailsRepository.recipeDetailsById(id)

    override suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(id)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }
}