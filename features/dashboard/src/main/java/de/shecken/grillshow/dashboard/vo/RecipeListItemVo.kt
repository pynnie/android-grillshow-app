package de.shecken.grillshow.dashboard.vo

data class RecipeListItemVo(
    val id: String,
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean
)
