package de.shecken.grillshow.category.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.toCategoryVo
import de.shecken.grillshow.vo.CategoryVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryInteractorImpl(private val recipeRepository: RecipeRepository) : CategoryInteractor {

    override fun loadCategory(id: String): Flow<CategoryVo?> =
        recipeRepository.getCategoryById(id).map { category -> category?.toCategoryVo() }

    override suspend fun updateFavoriteProperty(id: String, isFavorite: Boolean) {
        recipeRepository.getRecipeForId(id)?.let { recipe ->
            recipeRepository.updateRecipe(recipe.copy(isFavorite = isFavorite))
        }
    }

    override suspend fun updateCategories() {
        recipeRepository.fetchCategories()
    }
}