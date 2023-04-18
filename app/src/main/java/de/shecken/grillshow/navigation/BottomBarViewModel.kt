package de.shecken.grillshow.navigation

import androidx.lifecycle.ViewModel

class BottomBarViewModel(private val router: BottomBarRouter) : ViewModel() {

    fun onDashboardClick() = router.openDashboard()

    fun onFavoritesClick() = router.openFavorites()

    fun onInfoClick() = router.openInfo()
}