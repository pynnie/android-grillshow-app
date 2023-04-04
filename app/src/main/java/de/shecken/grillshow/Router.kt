package de.shecken.grillshow

import androidx.navigation.NavController
import de.shecken.favorites.favoritesRoute
import de.shecken.grillshow.shared.ui.navigation.BottomBarRouter
import de.shecken.grillshow.dashboard.navigation.DashboardRouter
import de.shecken.grillshow.dashboard.navigation.dashboardRoute
import de.shecken.grillshow.details.navigation.DetailsRouter
import de.shecken.grillshow.details.navigation.detailsScreen
import de.shecken.grillshow.shop.searchRoute

/**
 * Main Router class for the project. Should implement all sub-module Router interfaces using the [navController] injected from the
 * [MainActivity].
 */
internal class Router : DashboardRouter, BottomBarRouter, DetailsRouter {

    lateinit var navController: NavController

    /**
     * Defines the starting route of the app.
     */
    fun start() = dashboardRoute

    override fun openDashboard() = navController.navigate(dashboardRoute)

    override fun openSearch() = navController.navigate(searchRoute)

    override fun openFavorites() = navController.navigate(favoritesRoute)

    override fun openRecipeDetails(recipeId: String) =
        navController.navigate("$detailsScreen/$recipeId")

    override fun goBack() {
        navController.navigateUp()
    }
}
