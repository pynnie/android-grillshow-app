package de.shecken.grillshow.vo

data class RecipeDetailsVo(
    val id: String,
    val title: String,
    val ingredientlist: List<String>,
    val isFavorite: Boolean
)
