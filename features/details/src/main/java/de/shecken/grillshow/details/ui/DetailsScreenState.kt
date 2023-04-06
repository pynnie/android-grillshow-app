package de.shecken.grillshow.details.ui

import de.shecken.grillshow.repository.recipe.model.RecipeDetails

internal sealed class DetailsScreenState {

    object Loading : DetailsScreenState()

    data class Success(
        val recipeDetails: RecipeDetails
    ) : DetailsScreenState()

    object Failure : DetailsScreenState()
}
