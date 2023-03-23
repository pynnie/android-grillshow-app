package de.shecken.grillshow.video

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.shecken.grillshow.video.dashboard.DashboardScreen
import de.shecken.grillshow.video.search.SearchScreen

const val dashboardRoute = "dashboard_route"

const val dashboardScreen = "dashboard_screen"
const val searchScreen = "search_screen"

fun NavGraphBuilder.dashboardGraph() {
    navigation(startDestination = dashboardScreen, route = dashboardRoute) {
        composable(dashboardScreen) { DashboardScreen() }
        composable(searchScreen) { SearchScreen() }
    }
}
