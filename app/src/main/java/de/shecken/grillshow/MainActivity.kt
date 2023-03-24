package de.shecken.grillshow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shop.searchGraph
import de.shecken.grillshow.video.dashboardGraph
import org.koin.android.ext.android.inject

internal class MainActivity : AppCompatActivity() {

    private val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashScreen = true
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        super.onCreate(savedInstanceState)

        setContent {
            GrillshowTheme {
                val navController = rememberNavController()
                    .also { router.navController = it }

                LaunchedEffect(key1 = Unit) {
                    keepSplashScreen = false // Adjust for your app initialization process
                }

                NavHost(navController = navController, startDestination = router.start()) {
                    dashboardGraph()
                    searchGraph()
                }
            }
        }
    }
}
