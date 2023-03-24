package de.shecken.grillshow.shop

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.shecken.grillshow.shop.ui.SearchScreen

const val searchRoute = "search_route"

const val searchScreen = "search_screen"

fun NavGraphBuilder.searchGraph() {
    navigation(startDestination = searchScreen, route = searchRoute) {
        composable(searchScreen) { SearchScreen() }
    }
}
