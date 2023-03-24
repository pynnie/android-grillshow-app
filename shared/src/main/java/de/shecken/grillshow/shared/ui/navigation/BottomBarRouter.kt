package de.shecken.grillshow.shared.ui.navigation

interface BottomBarRouter {

    /**
     * Navigate to dashboard route
     */
    fun openDashboard()

    /**
     * Navigate to search route
     */
    fun openSearch()

    /**
     * Navigate to favorites route
     */
    fun openFavorites()
}