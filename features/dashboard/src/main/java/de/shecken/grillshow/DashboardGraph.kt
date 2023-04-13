package de.shecken.grillshow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import de.shecken.grillshow.dashboard.ui.DashboardScreen
import de.shecken.grillshow.details.ui.DetailsScreen

const val dashboardRoute = "dashboard_route"

const val dashboardScreen = "$dashboardRoute/dashboard_screen"
const val detailsScreen = "$dashboardScreen/details_screen"

const val recipeId = "recipeId"

fun NavGraphBuilder.dashboardGraph() {
    navigation(startDestination = dashboardScreen, route = dashboardRoute) {
        composable(dashboardScreen) { DashboardScreen() }
        composable(
            route = "$detailsScreen/{$recipeId}",
            arguments = listOf(
                navArgument(recipeId) { type = NavType.StringType })
        ) {
            DetailsScreen()
        }
    }
}
