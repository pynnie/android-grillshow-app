package de.shecken.grillshow.repository.recipe.model

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val isFavorite: Boolean
)
