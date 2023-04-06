package de.shecken.grillshow.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.details.navigation.DetailsRouter
import de.shecken.grillshow.details.navigation.recipeId
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val interactor: DetailsInteractor, savedStateHandle: SavedStateHandle,
    private val detailsRouter: DetailsRouter
) : ViewModel() {

    private val id = savedStateHandle[recipeId] ?: ""

    private val _detailsScreenState =
        MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val detailsScreenState: StateFlow<DetailsScreenState> = _detailsScreenState

    init {
        loadRecipeDetails()
    }

    fun onBackButtonClick() = detailsRouter.goBack()

    fun onFavIconClick(id: String, isFavorite: Boolean) = viewModelScope.launch {
        interactor.updateFavoriteProperty(id = id, isFavorite = isFavorite)
    }

    private fun loadRecipeDetails() {
        viewModelScope.launch {
            interactor.getRecipeDetails(id).collect { result ->
                _detailsScreenState.update {
                    result?.let { details ->
                        DetailsScreenState.Success(
                            recipeDetails = details,
                            onFavIconClick = ::onFavIconClick
                        )
                    } ?: DetailsScreenState.Failure
                }
            }
        }
    }
}