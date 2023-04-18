package de.shecken.grillshow

interface DashboardRouter {

    fun openRecipeDetails(recipeId: String)

    fun goBack()

    fun shareRecipe(recipeId: String)
}
