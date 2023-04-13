package de.shecken.grillshow.details.interactor

import de.shecken.grillshow.details.vo.RecipeDetailsVo
import de.shecken.grillshow.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.map

class DetailsInteractorImpl(
    private val recipeRepository: RecipeRepository
) :
    DetailsInteractor {

    override fun getRecipeDetails(id: String) = recipeRepository.getRecipeForIdAsFlow(id).map {
        it?.let { recipe ->
            RecipeDetailsVo(
                id = recipe.id,
                title = recipe.title,
                ingredientlist = extractIngredients(recipe.description),
                isFavorite = recipe.isFavorite
            )
        }
    }

    override suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(id)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }

    private fun extractIngredients(text: String) =
        text.lines()
            .filter { it.startsWith(INGREDIENT_PREFIX, ignoreCase = true) }
            .map { ingredient ->
                ingredient.replace(INGREDIENT_PREFIX, "").trim()
            }

    companion object {
        private const val INGREDIENT_PREFIX = "-"
    }
}