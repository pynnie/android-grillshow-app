package de.shecken.grillshow.di

import de.shecken.grillshow.category.CategoryViewModel
import de.shecken.grillshow.category.interactor.CategoryInteractor
import de.shecken.grillshow.category.interactor.CategoryInteractorImpl
import de.shecken.grillshow.dashboard.interactor.DashboardInteractor
import de.shecken.grillshow.dashboard.interactor.DashboardInteractorImpl
import de.shecken.grillshow.dashboard.ui.DashboardViewModel
import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.details.interactor.DetailsInteractorImpl
import de.shecken.grillshow.details.ui.DetailsViewModel
import de.shecken.grillshow.details.utils.IngredientExtractor
import de.shecken.grillshow.details.utils.IngredientExtractorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {

    viewModel { DashboardViewModel(get(), get()) }

    viewModel {
        DetailsViewModel(
            interactor = get(),
            savedStateHandle = get(),
            detailsRouter = get()
        )
    }

    viewModel { CategoryViewModel(router = get(), interactor = get(), savedStateHandle = get()) }

    factory<DashboardInteractor> {
        DashboardInteractorImpl(
            recipeRepository = get(),
            prefsRepository = get()
        )
    }

    single<DetailsInteractor> {
        DetailsInteractorImpl(recipeRepository = get(), ingredientExtractor = get())
    }
    single<CategoryInteractor> { CategoryInteractorImpl(recipeRepository = get()) }
    factory<IngredientExtractor> { IngredientExtractorImpl() }
}
