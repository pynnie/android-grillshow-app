package de.shecken.grillshow.info.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.shecken.grillshow.info.ui.InfoScreen

const val infoRoute = "info_route"

const val infoScreen = "info_screen"

fun NavGraphBuilder.infoGraph() {
    navigation(startDestination = infoScreen, route = infoRoute) {
        composable(infoScreen) { InfoScreen() }
    }
}