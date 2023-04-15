package de.shecken.grillshow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.shecken.favorites.navigation.favoritesGraph
import de.shecken.grillshow.repository.preferences.PreferencesRepository
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.ui.navigation.BottomBar
import de.shecken.grillshow.shop.navigation.searchGraph
import org.koin.android.ext.android.inject

@OptIn(ExperimentalMaterial3Api::class)
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
                    .also { router.navController = it }

                LaunchedEffect(key1 = Unit) {
                    prefsRepo.appPreferencesFlow.collect { prefs ->
                        if (prefs.isInitComplete) {
                            recipeRepo.fetchLatestRecipes()
                        } else {
                            recipeRepo.fetchAllRecipes()
                            recipeRepo.fetchCategories()
                        }
                        keepSplashScreen = false // Adjust for your app initialization process
                    }
                }
                Scaffold(bottomBar = { BottomBar() }) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = router.start()
                    ) {
                        dashboardGraph()
                        searchGraph()
                        favoritesGraph()
                    }
                }
            }
        }
    }
}
