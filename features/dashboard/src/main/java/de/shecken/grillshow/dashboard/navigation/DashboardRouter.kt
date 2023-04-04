package de.shecken.grillshow.dashboard.navigation

interface DashboardRouter {

    fun openRecipeDetails(recipeId: String)

    fun openSearch()
}
