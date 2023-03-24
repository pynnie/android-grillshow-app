package de.shecken.grillshow.shared.ui.navigation

import androidx.lifecycle.ViewModel

class BottomBarViewModel(private val router: BottomBarRouter) : ViewModel() {

    fun onDashboardClick() = router.openDashboard()

    fun onSearchClick() = router.openSearch()

    fun onFavoritesClick() = router.openFavorites()
}