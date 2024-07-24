package de.shecken.grillshow.navigation

import androidx.lifecycle.ViewModel

class BottomBarViewModel(private val router: BottomBarRouter) : ViewModel() {

    val contentScreenId = router.currentContentScreenId

    val show = router.currentRouteHasBottomBar

    fun navigateTo(contentScreenId: ContentScreenId) =
        with(router) {
            when (contentScreenId) {
                ContentScreenId.DASHBOARD -> openDashboard()
                ContentScreenId.FAVORITES -> openFavorites()
                ContentScreenId.INFO -> openInfo()
            }
        }

}