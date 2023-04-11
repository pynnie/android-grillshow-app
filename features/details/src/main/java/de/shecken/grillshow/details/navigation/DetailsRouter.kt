package de.shecken.grillshow.details.navigation

interface DetailsRouter {

    fun goBack()

    fun shareRecipe(recipeId: String)
}