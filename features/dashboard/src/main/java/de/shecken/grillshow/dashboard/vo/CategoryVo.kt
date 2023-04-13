package de.shecken.grillshow.dashboard.vo

data class CategoryVo(
    val id: String,
    val title: String,
    val recipes: List<RecipeListItemVo>
)