package de.shecken.grillshow.navigation

interface BottomBarRouter {

    /**
     * Navigate to dashboard route
     */
    fun openDashboard()

    /**
     * Navigate to favorites route
     */
    fun openFavorites()

    /**
     * Navigate to info route
     */
    fun openInfo()
}