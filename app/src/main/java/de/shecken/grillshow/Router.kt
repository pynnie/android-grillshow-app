package de.shecken.grillshow

import androidx.navigation.NavController
import de.shecken.grillshow.video.DashboardRouter
import de.shecken.grillshow.video.dashboardRoute
import de.shecken.grillshow.shop.ShopRouter
import de.shecken.grillshow.shop.shopRoute
import de.shecken.grillshow.video.searchScreen

/**
 * Main Router class for the project. Should implement all sub-module Router interfaces using the [navController] injected from the
 * [MainActivity].
 */
internal class Router : ShopRouter, DashboardRouter {

    lateinit var navController: NavController

    /**
     * Defines the starting route of the app.
     */
    fun start() = dashboardRoute

    override fun navigateToDetails() = navController.navigate(searchScreen)

    override fun finishDetails() = navController.navigate(shopRoute)

    override fun finishShopModule() = navController.navigate(dashboardRoute)
}
