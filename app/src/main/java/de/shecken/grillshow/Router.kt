package de.shecken.grillshow

import androidx.navigation.NavController
import de.shecken.grillshow.video.DashboardRouter
import de.shecken.grillshow.video.dashboardRoute
import de.shecken.grillshow.shop.SearchRouter
import de.shecken.grillshow.shop.searchRoute

/**
 * Main Router class for the project. Should implement all sub-module Router interfaces using the [navController] injected from the
 * [MainActivity].
 */
internal class Router : SearchRouter, DashboardRouter {

    lateinit var navController: NavController

    /**
     * Defines the starting route of the app.
     */
    fun start() = dashboardRoute

    override fun openRecipeDetails() = navController.navigate(searchRoute)

    override fun openSearch() = navController.navigate(searchRoute)

    override fun finishSearchModule() = navController.navigate(dashboardRoute)
}
