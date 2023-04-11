package de.shecken.grillshow.shop.di

import de.shecken.grillshow.shop.interactor.SearchInteractor
import de.shecken.grillshow.shop.interactor.SearchInteractorImpl
import de.shecken.grillshow.shop.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(interactor = get(), router = get()) }
    single<SearchInteractor> { SearchInteractorImpl(recipeRepository = get()) }
}
