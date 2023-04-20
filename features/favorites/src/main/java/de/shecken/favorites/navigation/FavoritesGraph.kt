package de.shecken.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.shecken.favorites.ui.FavoriteScreen

const val favoritesRoute = "favorites_route"

const val favoritesScreen = "$favoritesRoute/favorites_screen"

fun NavGraphBuilder.favoritesGraph() {
    navigation(startDestination = favoritesScreen, route = favoritesRoute) {
        composable(favoritesScreen) { FavoriteScreen() }
    }
}