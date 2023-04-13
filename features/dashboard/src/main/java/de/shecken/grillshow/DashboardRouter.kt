package de.shecken.grillshow

interface DashboardRouter {

    fun openRecipeDetails(recipeId: String)

    fun openSearch()

    fun goBack()

    fun shareRecipe(recipeId: String)
}
