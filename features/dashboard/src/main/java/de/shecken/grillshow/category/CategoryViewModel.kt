package de.shecken.grillshow.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.category.interactor.CategoryInteractor
import de.shecken.grillshow.navigation.DashboardRouter
import de.shecken.grillshow.navigation.categoryId
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val router: DashboardRouter,
    private val interactor: CategoryInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle[categoryId] ?: ""

    private var isLoading = MutableStateFlow(false)

    private val _categoryScreenState = getCategoryScreenState()
    val screenState: StateFlow<CategoryScreenState> =
        combine(_categoryScreenState, isLoading) { categoryState, loading ->
            if (loading) {
                CategoryScreenState.Loading
            } else {
                categoryState
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CategoryScreenState.Loading
        )


    private fun getCategoryScreenState() =
        interactor.loadCategory(id).map { result ->
            result?.let { category ->
                if (category.recipes.isNotEmpty()) {
                    CategoryScreenState.Success(
                        category = category,
                        onFavIconClick = ::onFavIconClick,
                        onRecipeClick = ::onRecipeClick
                    )
                } else {
                    null
                }
            } ?: CategoryScreenState.Failure(::onReloadButtonClick)
        }

    fun onReloadButtonClick() {
        isLoading.update { true }
        viewModelScope.launch {
            interactor.updateCategories()
            isLoading.update { false }
        }
    }

    fun onRecipeClick(recipeId: String) = router.openRecipeDetails(recipeId)

    fun onFavIconClick(id: String, isFavorite: Boolean) = viewModelScope.launch {
        interactor.updateFavoriteProperty(id = id, isFavorite = isFavorite)
    }

    fun goBack() = router.goBack()
}