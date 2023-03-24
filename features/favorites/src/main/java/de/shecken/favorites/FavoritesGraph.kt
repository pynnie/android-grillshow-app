package de.shecken.favorites

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

const val favoritesRoute = "favorites_route"

const val favoritesScreen = "favorites_screen"

fun NavGraphBuilder.favoritesGraph() {
    navigation(startDestination = favoritesScreen, route = favoritesRoute) {
        composable(favoritesScreen) { FavoriteScreen() }
    }
}