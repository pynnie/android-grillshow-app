package de.shecken.grillshow.dashboard

import de.shecken.grillshow.dashboard.vo.CategoryVo
import de.shecken.grillshow.dashboard.vo.RecipeListItemVo
import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe

val fakeRecipe1 = Recipe(
    id = "1",
    title = "FakeTitle1",
    description = "description1",
    thumbnailUrl = "imageUrl1",
    isFavorite = false
)

val fakeRecipe2 = Recipe(
    id = "2",
    title = "FakeTitle2",
    description = "description2",
    thumbnailUrl = "imageUrl2",
    isFavorite = true
)


val fakeCategory1 = Category(
    id = "1",
    title = "FakeTitle1",
    description = "description1",
    recipes = listOf(fakeRecipe1, fakeRecipe2)
)

val fakeRecipeListItemVo1 = RecipeListItemVo(
    id = "1",
    title = "FakeTitle1",
    imageUrl = "imageUrl1",
    isFavorite = false
)

val fakeRecipeListItemVo2 = RecipeListItemVo(
    id = "2",
    title = "FakeTitle2",
    imageUrl = "imageUrl2",
    isFavorite = true
)

val fakeCategoryVo1 = CategoryVo(
    id = "1",
    title = "FakeTitle1",
    recipes = listOf(fakeRecipeListItemVo1, fakeRecipeListItemVo2)
)