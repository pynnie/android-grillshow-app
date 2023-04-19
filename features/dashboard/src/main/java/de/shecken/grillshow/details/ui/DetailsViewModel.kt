package de.shecken.grillshow.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.DashboardRouter
import de.shecken.grillshow.recipeId
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val interactor: DetailsInteractor, savedStateHandle: SavedStateHandle,
    private val detailsRouter: DashboardRouter
) : ViewModel() {

    private val id = savedStateHandle[recipeId] ?: ""

    private val _detailsScreenState = loadRecipeDetails()
    val detailsScreenState: StateFlow<DetailsScreenState> = _detailsScreenState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        DetailsScreenState.Loading
    )

    fun onBackButtonClick() = detailsRouter.goBack()

    fun onFavIconClick(id: String, isFavorite: Boolean) = viewModelScope.launch {
        interactor.updateFavoriteProperty(id = id, isFavorite = isFavorite)
    }

    fun onShareIconClick(id: String) = detailsRouter.shareRecipe(id)

    fun onVideoButtonClick() =
        detailsRouter.openVideo((detailsScreenState.value as DetailsScreenState.Success).recipeDetails.id)

    private fun loadRecipeDetails() =
        interactor.getRecipeDetails(id).map { result ->
            result?.let { details ->
                DetailsScreenState.Success(
                    recipeDetails = details,
                    onFavIconClick = ::onFavIconClick,
                    onVideoButtonClick = ::onVideoButtonClick
                )
            } ?: DetailsScreenState.Failure(::onBackButtonClick)
        }
}