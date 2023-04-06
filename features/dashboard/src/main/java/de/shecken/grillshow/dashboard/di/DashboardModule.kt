package de.shecken.grillshow.dashboard.di

import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.dashboard.interactor.DashboardInteractorImpl
import de.shecken.grillshow.dashboard.ui.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {

    viewModel { DashboardViewModel(get(), get()) }

    factory<DashboardInteractor> { DashboardInteractorImpl(get()) }
}
