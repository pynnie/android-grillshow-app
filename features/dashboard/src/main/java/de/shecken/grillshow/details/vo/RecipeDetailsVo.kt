package de.shecken.grillshow.details.vo

data class RecipeDetailsVo(
    val id: String,
    val title: String,
    val ingredientlist: List<String>,
    val isFavorite: Boolean
)
