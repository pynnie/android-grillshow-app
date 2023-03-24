package de.shecken.grillshow

import de.shecken.grillshow.video.DashboardRouter
import de.shecken.grillshow.shop.SearchRouter
import org.koin.dsl.module

internal val mainModule = module {

    single { Router() }

    factory<SearchRouter> { get<Router>() }

    factory<DashboardRouter> { get<Router>() }
}
