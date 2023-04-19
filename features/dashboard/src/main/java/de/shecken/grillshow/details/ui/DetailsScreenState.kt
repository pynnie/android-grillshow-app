package de.shecken.grillshow.details.ui

import de.shecken.grillshow.details.vo.RecipeDetailsVo

internal sealed class DetailsScreenState {

    object Loading : DetailsScreenState()

    data class Success(
        val recipeDetails: RecipeDetailsVo,
        val onFavIconClick: (String, Boolean) -> Unit,
        val onVideoButtonClick: () -> Unit
    ) : DetailsScreenState()

    data class Failure(val onBackButtonClick: () -> Unit) : DetailsScreenState()
}
