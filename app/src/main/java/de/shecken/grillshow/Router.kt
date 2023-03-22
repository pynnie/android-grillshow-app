package de.shecken.grillshow

import androidx.navigation.NavController
import de.shecken.grillshow.video.VideoRouter
import de.shecken.grillshow.video.videoRoute
import de.shecken.grillshow.shop.ShopRouter
import de.shecken.grillshow.shop.shopRoute
import de.shecken.grillshow.video.videoScreen2

/**
 * Main Router class for the project. Should implement all sub-module Router interfaces using the [navController] injected from the
 * [MainActivity].
 */
internal class Router : ShopRouter, VideoRouter {

    lateinit var navController: NavController

    /**
     * Defines the starting route of the app.
     */
    fun start() = videoRoute

    override fun navigateToDetails() = navController.navigate(videoScreen2)

    override fun finishDetails() = navController.navigate(shopRoute)

    override fun finishShopModule() = navController.navigate(videoRoute)
}
