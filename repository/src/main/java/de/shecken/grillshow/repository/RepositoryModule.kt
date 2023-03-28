package de.shecken.grillshow.repository

import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.RecipeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<RecipeRepository> { RecipeRepositoryImpl(get(), get(), get()) }
}
