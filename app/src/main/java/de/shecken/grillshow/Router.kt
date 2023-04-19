package de.shecken.grillshow

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.navigation.NavController
import de.shecken.favorites.navigation.FavoritesRouter
import de.shecken.favorites.navigation.favoritesRoute
import de.shecken.grillshow.legal.LegalScreenType
import de.shecken.grillshow.navigation.*

/**
 * Main Router class for the project. Should implement all sub-module Router interfaces using the [navController] injected from the
 * [MainActivity].
 */
internal class Router(private val context: Context) : DashboardRouter, BottomBarRouter,
    FavoritesRouter, InfoRouter {

    lateinit var navController: NavController

    /**
     * Defines the starting route of the app.
     */
    fun start() = dashboardRoute

    override fun openDashboard() = navController.navigate(dashboardRoute)

    override fun openFavorites() = navController.navigate(favoritesRoute)

    override fun openInfo() = navController.navigate(infoRoute)

    override fun openRecipeDetails(recipeId: String) =
        navController.navigate("$detailsScreen/$recipeId")

    override fun goBack() {
        navController.navigateUp()
    }

    override fun shareRecipe(recipeId: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            val videoUrl = context.getString(R.string.youtube_video_url_template, recipeId)
            putExtra(Intent.EXTRA_TEXT, context.getString(R.string.details_share_message, videoUrl))
            type = INTENT_TYPE
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

    override fun openLicenses() = navController.navigate(licensesScreen)

    override fun openURL(url: String) = openExternalUrl(url)

    override fun openURL(@StringRes urlRes: Int) = openURL(context.getString(urlRes))

    override fun openVideo(videoId: String) {
        val videoUrl = context.getString(R.string.youtube_video_url_template, videoId)
        openExternalUrl(videoUrl)
    }

    override fun openEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${context.getString(R.string.info_contact_mail_address)}")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun openLegalPage(legalType: LegalScreenType) =
        navController.navigate("$legalScreen/${legalType.name}")

    private fun openExternalUrl(url: String) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    companion object {
        private const val INTENT_TYPE = "text/plain"
    }
}
