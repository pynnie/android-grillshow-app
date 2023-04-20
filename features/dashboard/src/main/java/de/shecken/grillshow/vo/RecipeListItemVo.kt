package de.shecken.grillshow.vo

data class RecipeListItemVo(
    val id: String,
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean
)
