package de.shecken.grillshow.navigation

import kotlinx.coroutines.flow.StateFlow

interface BottomBarRouter {

    /**
     * The current route, published by the navController
     */
    var currentRoute: StateFlow<String>

    /**
     * Whether the current route has a bottombar.
     * The bottombar will hide itself if false.
     */
    var currentRouteHasBottomBar: StateFlow<Boolean>

    /**
     * [currentRoute] mapped on to [ContentScreenId].
     * Sub routes will map onto the same [ContentScreenId]
     */
    var currentContentScreenId: StateFlow<ContentScreenId>

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