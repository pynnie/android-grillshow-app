package de.shecken.grillshow.repository.recipe.model

data class Category(
    val id: String,
    val title: String,
    val description: String,
    val recipes: List<Recipe>
)
