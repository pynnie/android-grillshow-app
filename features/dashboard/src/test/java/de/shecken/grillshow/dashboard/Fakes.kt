package de.shecken.grillshow.dashboard

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