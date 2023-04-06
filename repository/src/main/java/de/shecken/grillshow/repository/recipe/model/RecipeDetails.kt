package de.shecken.grillshow.repository.recipe.model

data class RecipeDetails(
    val id: String,
    val title: String,
    val ingredientlist: List<String>,
    val isFavorite: Boolean
)
