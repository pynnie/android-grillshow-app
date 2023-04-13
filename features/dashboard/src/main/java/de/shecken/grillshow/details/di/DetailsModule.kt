package de.shecken.grillshow.details.di

import de.shecken.grillshow.details.interactor.DetailsInteractor
import de.shecken.grillshow.details.interactor.DetailsInteractorImpl
import de.shecken.grillshow.details.ui.DetailsViewModel
import de.shecken.grillshow.details.utils.IngredientExtractor
import de.shecken.grillshow.details.utils.IngredientExtractorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    viewModel {
        DetailsViewModel(
            interactor = get(),
            savedStateHandle = get(),
            detailsRouter = get()
        )
    }

    single<DetailsInteractor> {
        DetailsInteractorImpl(recipeRepository = get(), ingredientExtractor = get())
    }
    factory<IngredientExtractor> { IngredientExtractorImpl() }
}