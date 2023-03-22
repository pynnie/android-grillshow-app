package de.shecken.grillshow

import de.shecken.grillshow.video.VideoRouter
import de.shecken.grillshow.shop.ShopRouter
import org.koin.dsl.module

internal val mainModule = module {

    single { Router() }

    factory<ShopRouter> { get<Router>() }

    factory<VideoRouter> { get<Router>() }
}
