package de.shecken.grillshow.category

import de.shecken.grillshow.vo.CategoryVo

sealed class CategoryScreenState {

    object Loading : CategoryScreenState()

    data class Success(
        val category: CategoryVo,
        val onFavIconClick: (String, Boolean) -> Unit,
        val onRecipeClick: (String) -> Unit
    ) : CategoryScreenState()

    data class Failure(val onReloadButtonClick: () -> Unit) : CategoryScreenState()
}