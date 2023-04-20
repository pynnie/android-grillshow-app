package de.shecken.grillshow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import de.shecken.grillshow.category.CategoryScreen
import de.shecken.grillshow.dashboard.ui.DashboardScreen
import de.shecken.grillshow.details.ui.DetailsScreen

const val dashboardRoute = "dashboard_route"

const val dashboardScreen = "$dashboardRoute/dashboard_screen"
const val detailsScreen = "$dashboardScreen/details_screen"
const val categoryScreen = "$dashboardScreen/category_screen"

const val recipeId = "recipeId"
const val categoryId = "categoryId"

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
        composable(
            route = "$categoryScreen/{$categoryId}",
            arguments = listOf(
                navArgument(categoryId) { type = NavType.StringType })
        ) {
            CategoryScreen()
        }
    }
}
