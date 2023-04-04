package de.shecken.grillshow.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.details.navigation.recipeId
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val interactor: DetailsInteractor, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle[recipeId] ?: ""

    private val _detailsScreenState =
        MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val detailsScreenState: StateFlow<DetailsScreenState> = _detailsScreenState

    init {
        loadRecipeDetails()
    }

    private fun loadRecipeDetails() {
        viewModelScope.launch {
            interactor.getRecipeDetails(id).collect { result ->
                _detailsScreenState.update {
                    result?.let { details ->
                        DetailsScreenState.Success(details)
                    } ?: DetailsScreenState.Failure
                }
            }
        }
    }
}