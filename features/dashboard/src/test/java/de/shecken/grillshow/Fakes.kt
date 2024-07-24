package de.shecken.grillshow.dashboard

import de.shecken.grillshow.vo.CategoryVo
import de.shecken.grillshow.vo.RecipeListItemVo
import de.shecken.grillshow.vo.RecipeDetailsVo
import de.shecken.grillshow.repository.recipe.model.Category
import de.shecken.grillshow.repository.recipe.model.Recipe

val ingredient1 = "zutat1"
val ingredient2 = "zutat2"

val fakeRecipe1 = Recipe(
    id = "1",
    title = "FakeTitle1",
    description = "description1 \n-$ingredient1\n-$ingredient2",
    thumbnailUrl = "imageUrl1",
    isFavorite = false
)

val fakeRecipe2 = Recipe(
    id = "2",
    title = "FakeTitle2",
    description = "description2  \n-$ingredient1\n",
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
    description = "description1",
    recipes = listOf(fakeRecipeListItemVo1, fakeRecipeListItemVo2)
)

val fakeDetailsVo1 = RecipeDetailsVo(
    id = "1",
    title = "FakeTitle1",
    ingredientlist = listOf(ingredient1, ingredient2),
    isFavorite = false
)