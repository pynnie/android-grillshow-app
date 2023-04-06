package de.shecken.grillshow.repository.details

import de.shecken.grillshow.database.recipe.RecipeDao
import de.shecken.grillshow.repository.recipe.model.RecipeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailsRepositoryImpl(private val recipeDao: RecipeDao) : DetailsRepository {

    override fun recipeDetailsById(id: String): Flow<RecipeDetails?> =
        recipeDao.getRecipeFlowById(id).map { result ->
            result?.let { recipeEntity ->
                RecipeDetails(
                    id = recipeEntity.id,
                    title = recipeEntity.title,
                    ingredientlist = extractIngredients(recipeEntity.description),
                    isFavorite = recipeEntity.isFavorite
                )
            }
        }

    private fun extractIngredients(text: String) =
        text.lines().filter { it.startsWith(INGREDIENT_PREFIX, ignoreCase = true) }
            .map { ingredient ->
                ingredient.replace(
                    INGREDIENT_PREFIX, ""
                ).trim()
            }

    companion object {
        private const val INGREDIENT_PREFIX = "-"
    }
}