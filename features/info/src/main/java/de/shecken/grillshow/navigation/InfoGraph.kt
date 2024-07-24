package de.shecken.grillshow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import de.shecken.grillshow.info.ui.InfoScreen
import de.shecken.grillshow.legal.LegalScreen
import de.shecken.grillshow.licenses.LicensesScreen

const val infoRoute = "info_route"

const val infoScreen = "$infoRoute/info_screen"
const val licensesScreen = "$infoRoute/licenses_screen"
const val legalScreen = "$infoScreen/legal_screen"

const val legalType = "legal_type"

fun NavGraphBuilder.infoGraph() {
    navigation(startDestination = infoScreen, route = infoRoute) {
        composable(infoScreen) { InfoScreen() }
        composable(licensesScreen) { LicensesScreen() }
        composable(
            route = "$legalScreen/{$legalType}",
            arguments = listOf(
                navArgument(legalType) { type = NavType.StringType })
        ) {
            LegalScreen()
        }
    }
}