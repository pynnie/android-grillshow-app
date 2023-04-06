package de.shecken.grillshow.repository

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import de.shecken.grillshow.repository.details.DetailsRepository
import de.shecken.grillshow.repository.details.DetailsRepositoryImpl
import de.shecken.grillshow.repository.preferences.PreferencesRepository
import de.shecken.grillshow.repository.preferences.PreferencesRepositoryImpl
import de.shecken.grillshow.repository.recipe.RecipeRepository
import de.shecken.grillshow.repository.recipe.RecipeRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATA_STORE_FILE = "grillshow_preferences"

val repositoryModule = module {

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            api = get(),
            recipeDao = get(),
            categoryDao = get(),
            preferencesRepository = get(),
            stringProvider = get()
        )
    }

    single<DetailsRepository> { DetailsRepositoryImpl(get()) }

    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }

    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { androidContext().preferencesDataStoreFile(DATA_STORE_FILE) }
        )
    }
}
