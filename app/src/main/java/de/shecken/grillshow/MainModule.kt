package de.shecken.grillshow

import de.shecken.favorites.navigation.FavoritesRouter
import de.shecken.grillshow.info.navigation.InfoRouter
import de.shecken.grillshow.navigation.BottomBarRouter
import de.shecken.grillshow.navigation.BottomBarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val mainModule = module {

    single { Router(get()) }
    factory<DashboardRouter> { get<Router>() }
    factory<BottomBarRouter> { get<Router>() }
    factory<FavoritesRouter> { get<Router>() }
    factory<InfoRouter> { get<Router>() }

    viewModel { BottomBarViewModel(get()) }
}
