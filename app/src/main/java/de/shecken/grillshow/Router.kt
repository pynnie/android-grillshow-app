package de.shecken.grillshow

import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import de.shecken.favorites.navigation.FavoritesRouter
import de.shecken.favorites.navigation.favoritesRoute
import de.shecken.grillshow.shared.ui.navigation.BottomBarRouter
import de.shecken.grillshow.shop.navigation.SearchRouter
import de.shecken.grillshow.shop.navigation.searchRoute

/**
 * Main Router class for the project. Should implement all sub-module Router interfaces using the [navController] injected from the
 * [MainActivity].
 */
internal class Router(private val context: Context) : DashboardRouter, BottomBarRouter,
    FavoritesRouter,
    SearchRouter {

    lateinit var navController: NavController

    /**
     * Defines the starting route of the app.
     */
    fun start() = dashboardRoute

    override fun openDashboard() = navController.navigate(dashboardRoute)

    override fun openSearch() = navController.navigate(searchRoute)

    override fun openFavorites() = navController.navigate(favoritesRoute)

    override fun openRecipeDetails(recipeId: String) =
        navController.navigate("$detailsScreen/$recipeId")

    override fun goBack() {
        navController.navigateUp()
    }

    override fun shareRecipe(recipeId: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, context.getString(R.string.details_share_message, recipeId))
            type = INTENT_TYPE
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

    companion object {
        private const val INTENT_TYPE = "text/plain"
    }
}
