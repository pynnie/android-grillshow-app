package de.shecken.grillshow.details

import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.details.interactor.DetailsInteractorImpl
import de.shecken.grillshow.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    viewModel { DetailsViewModel(get(), get()) }

    single<DetailsInteractor> { DetailsInteractorImpl(get()) }
}