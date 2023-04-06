package de.shecken.grillshow.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.shecken.grillshow.dashboard.ui.DashboardScreen

const val dashboardRoute = "dashboard_route"

const val dashboardScreen = "dashboard_screen"

fun NavGraphBuilder.dashboardGraph() {
    navigation(startDestination = dashboardScreen, route = dashboardRoute) {
        composable(dashboardScreen) { DashboardScreen() }
    }
}
