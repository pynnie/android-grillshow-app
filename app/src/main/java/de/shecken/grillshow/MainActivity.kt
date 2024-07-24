package de.shecken.grillshow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.shecken.favorites.navigation.favoritesGraph
import de.shecken.grillshow.navigation.BottomNavigationScreen
import de.shecken.grillshow.navigation.dashboardGraph
import de.shecken.grillshow.navigation.infoGraph
import de.shecken.grillshow.repository.preferences.PreferencesRepository
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.shared.GrillshowTheme
import org.koin.android.ext.android.inject

internal class MainActivity : AppCompatActivity() {

    private val router: Router by inject()
    private val recipeRepo: RecipeRepository by inject()
    private val prefsRepo: PreferencesRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashScreen = true
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        super.onCreate(savedInstanceState)

        setContent {
            GrillshowTheme {
                val navController = rememberNavController()
                    .also { router.setNavController(it) }

                LaunchedEffect(key1 = Unit) {
                    updateAppVersion()
                    prefsRepo.appPreferencesFlow.collect { prefs ->
                        if (prefs.isInitComplete) {
                            recipeRepo.fetchLatestRecipes()
                        } else {
                            recipeRepo.fetchAllRecipes()
                            recipeRepo.fetchCategories()
                        }
                        keepSplashScreen = false
                    }
                }

                BottomNavigationScreen {
                    NavHost(
                        navController = navController,
                        startDestination = router.start()
                    ) {
                        dashboardGraph()
                        favoritesGraph()
                        infoGraph()
                    }
                }
            }
        }
    }

    private suspend fun updateAppVersion() {
        prefsRepo.updateAppVersion(BuildConfig.VERSION_NAME)
    }
}
