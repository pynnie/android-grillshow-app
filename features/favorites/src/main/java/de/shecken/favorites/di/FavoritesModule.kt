package de.shecken.favorites.di

import de.shecken.favorites.interactor.FavoritesInteractor
import de.shecken.favorites.interactor.FavoritesInteractorImpl
import de.shecken.favorites.ui.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoritesViewModel(favoritesRouter = get(), interactor = get()) }
    single<FavoritesInteractor> { FavoritesInteractorImpl(recipeRepository = get()) }
}