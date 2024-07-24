package de.shecken.grillshow.navigation

interface DashboardRouter {

    fun openRecipeDetails(recipeId: String)

    fun goBack()

    fun shareRecipe(recipeId: String)

    fun openVideo(videoId: String)

    fun openCategory(categoryId: String)
}
