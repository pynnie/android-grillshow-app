package de.shecken.grillshow.shop

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

const val shopRoute = "shop_route"

const val shopScreen = "shop_screen"

fun NavGraphBuilder.userGraph() {
    navigation(startDestination = shopScreen, route = shopRoute) {
        composable(shopScreen) {  }
    }
}
