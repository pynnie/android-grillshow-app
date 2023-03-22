package de.shecken.grillshow.video

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.shecken.grillshow.video.dashboard.DashboardScreen
import de.shecken.grillshow.video.search.VideoScreen2

const val videoRoute = "video_route"

const val videoScreen1 = "video_screen_1"
const val videoScreen2 = "video_screen_2"

fun NavGraphBuilder.videoGraph() {
    navigation(startDestination = videoScreen1, route = videoRoute) {
        composable(videoScreen1) { DashboardScreen() }
        composable(videoScreen2) { VideoScreen2() }
    }
}
