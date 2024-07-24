package de.shecken.favorites.navigation

interface FavoritesRouter {

    fun openRecipeDetails(recipeId: String)

    fun openDashboard()
}