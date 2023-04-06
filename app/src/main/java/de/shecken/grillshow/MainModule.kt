package de.shecken.grillshow

import de.shecken.grillshow.shared.ui.navigation.BottomBarRouter
import de.shecken.grillshow.dashboard.navigation.DashboardRouter
import de.shecken.grillshow.details.navigation.DetailsRouter
import org.koin.dsl.module

internal val mainModule = module {

    single { Router() }
    factory<DashboardRouter> { get<Router>() }
    factory<BottomBarRouter> { get<Router>() }
    factory<DetailsRouter> { get<Router>() }
}
