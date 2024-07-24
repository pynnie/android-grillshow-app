package de.shecken.grillshow.vo

data class CategoryVo(
    val id: String,
    val title: String,
    val description: String,
    val recipes: List<RecipeListItemVo>
)