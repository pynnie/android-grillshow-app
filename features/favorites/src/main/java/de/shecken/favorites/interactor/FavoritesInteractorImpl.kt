package de.shecken.favorites.interactor

import de.shecken.favorites.vo.FavoriteVo
import de.shecken.grillshow.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesInteractorImpl(private val recipeRepository: RecipeRepository) :
    FavoritesInteractor {

    override fun getFavoriteRecipes(): Flow<List<FavoriteVo>> =
        recipeRepository.getAllFavorites().map { recipes ->
            recipes.map { recipe ->
                FavoriteVo(
                    id = recipe.id,
                    title = recipe.title,
                    imageUrl = recipe.thumbnailUrl
                )
            }
        }
}