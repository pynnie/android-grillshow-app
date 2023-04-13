package de.shecken.grillshow

import de.shecken.favorites.navigation.FavoritesRouter
import de.shecken.grillshow.shared.ui.navigation.BottomBarRouter
import de.shecken.grillshow.shop.navigation.SearchRouter
import org.koin.dsl.module

internal val mainModule = module {

    single { Router(get()) }
    factory<DashboardRouter> { get<Router>() }
    factory<BottomBarRouter> { get<Router>() }
    factory<FavoritesRouter> { get<Router>() }
    factory<SearchRouter> { get<Router>() }
}
