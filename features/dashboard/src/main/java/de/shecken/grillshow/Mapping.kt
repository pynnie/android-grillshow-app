package de.shecken.grillshow

import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe
import de.shecken.grillshow.vo.CategoryVo
import de.shecken.grillshow.vo.RecipeListItemVo
import de.shecken.grillshow.vo.SearchResultVo

fun Category.toCategoryVo() =
    CategoryVo(
        id = id,
        title = title,
        description = description,
        recipes = recipes.map { recipe ->
            RecipeListItemVo(
                id = recipe.id,
                title = recipe.title,
                imageUrl = recipe.thumbnailUrl,
                isFavorite = recipe.isFavorite
            )
        }
    )

fun Recipe.toSearchResultVo() =
    SearchResultVo(
        id = id,
        title = title,
        imageUrl = thumbnailUrl
    )