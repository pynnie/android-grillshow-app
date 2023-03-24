package de.shecken.grillshow.shop.ui

import androidx.lifecycle.ViewModel
import de.shecken.grillshow.shop.SearchRouter

internal class SearchViewModel(private val searchRouter: SearchRouter) : ViewModel() {

    fun onBackButtonClick() = searchRouter.finishSearchModule()
}
