package de.shecken.grillshow.details.interactor

import de.shecken.grillshow.details.utils.IngredientExtractor
import de.shecken.grillshow.details.vo.RecipeDetailsVo
import de.shecken.grillshow.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.map

class DetailsInteractorImpl(
    private val recipeRepository: RecipeRepository,
    private val ingredientExtractor: IngredientExtractor
) :
    DetailsInteractor {

    override fun getRecipeDetails(id: String) = recipeRepository.getRecipeForIdAsFlow(id).map {
        it?.let { recipe ->
            RecipeDetailsVo(
                id = recipe.id,
                title = recipe.title,
                ingredientlist = ingredientExtractor.extractIngredientsFromText(recipe.description),
                isFavorite = recipe.isFavorite
            )
        }
    }

    override suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(id)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }

}