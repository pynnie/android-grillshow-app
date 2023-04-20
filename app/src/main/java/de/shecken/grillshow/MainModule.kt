package de.shecken.grillshow

import de.shecken.favorites.navigation.FavoritesRouter
import de.shecken.grillshow.navigation.InfoRouter
import de.shecken.grillshow.navigation.BottomBarRouter
import de.shecken.grillshow.navigation.BottomBarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

internal val mainModule = module {

    single {
        Router(
            context = get(),
            ioDispatcher = Dispatchers.IO,
            coroutineScope = CoroutineScope(Dispatchers.IO)
        )
    } binds arrayOf(
        DashboardRouter::class,
        FavoritesRouter::class,
        InfoRouter::class,
        BottomBarRouter::class,
    )

    viewModel { BottomBarViewModel(get()) }
}
