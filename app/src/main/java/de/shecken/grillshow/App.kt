package de.shecken.grillshow

import android.app.Application
import de.shecken.grillshow.database.di.databaseModule
import de.shecken.grillshow.networking.youtube.networkModule
import de.shecken.grillshow.video.di.dashboardModule
import de.shecken.grillshow.repository.repositoryModule
import de.shecken.grillshow.shop.di.searchModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@ExperimentalSerializationApi
internal class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    mainModule,
                    searchModule,
                    dashboardModule
                )
            )
        }
    }

    private fun initTimber() = Timber.plant(Timber.DebugTree())
}
