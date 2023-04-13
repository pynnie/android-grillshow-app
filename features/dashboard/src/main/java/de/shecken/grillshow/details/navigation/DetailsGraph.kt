package de.shecken.grillshow.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import de.shecken.grillshow.details.ui.DetailsScreen

const val detailsScreen = "details_screen"

const val recipeId = "recipeId"

fun NavGraphBuilder.detailsGraph() {
    composable(
        route = "$detailsScreen/{$recipeId}",
        arguments = listOf(
            navArgument(recipeId) { type = NavType.StringType })
    ) {
        DetailsScreen()
    }
}
