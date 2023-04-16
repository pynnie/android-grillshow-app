package de.shecken.grillshow.info.di

import de.shecken.grillshow.info.interactor.InfoInteractor
import de.shecken.grillshow.info.interactor.InfoInteractorImpl
import de.shecken.grillshow.info.ui.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val infoModule = module {
    viewModel { InfoViewModel(interactor = get(), router = get()) }

    single<InfoInteractor> { InfoInteractorImpl(preferencesRepository = get()) }
}