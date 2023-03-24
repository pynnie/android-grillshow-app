package de.shecken.grillshow.video.di

import de.shecken.grillshow.video.dashboard.DashboardInteractor
import de.shecken.grillshow.video.dashboard.DashboardInteractorImpl
import de.shecken.grillshow.video.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {

    viewModel { DashboardViewModel(get(), get()) }

    factory<DashboardInteractor> { DashboardInteractorImpl(get()) }
}
