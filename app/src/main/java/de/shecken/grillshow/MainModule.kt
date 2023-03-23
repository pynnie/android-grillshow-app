package de.shecken.grillshow

import de.shecken.grillshow.video.DashboardRouter
import de.shecken.grillshow.shop.ShopRouter
import org.koin.dsl.module

internal val mainModule = module {

    single { Router() }

    factory<ShopRouter> { get<Router>() }

    factory<DashboardRouter> { get<Router>() }
}
