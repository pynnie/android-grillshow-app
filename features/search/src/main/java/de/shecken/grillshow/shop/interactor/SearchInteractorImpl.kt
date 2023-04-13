package de.shecken.grillshow.shop.interactor

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.shop.vo.SearchResultVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchInteractorImpl(private val recipeRepository: RecipeRepository) : SearchInteractor {

    override fun searchForRecipes(query: String): Flow<List<SearchResultVo>> =
        recipeRepository.searchRecipes(query).map { recipes ->
            recipes.map { recipe ->
                SearchResultVo(
                    id = recipe.id,
                    title = recipe.title,
                    imageUrl = recipe.thumbnailUrl
                )
            }
        }
}
