package de.shecken.grillshow.video.di

import de.shecken.grillshow.video.dashboard.DashboardInteractor
import de.shecken.grillshow.video.dashboard.DashboardInteractorImpl
import de.shecken.grillshow.video.dashboard.DashboardViewModel
import de.shecken.grillshow.video.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val documentModule = module {

    viewModel { DashboardViewModel(get(), get()) }

    viewModel { SearchViewModel(get()) }

    factory<DashboardInteractor> { DashboardInteractorImpl(get()) }
}
