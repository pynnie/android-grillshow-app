package de.shecken.grillshow.info.interactor

import de.shecken.grillshow.repository.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InfoInteractorImpl(private val preferencesRepository: PreferencesRepository) :
    InfoInteractor {

    override val versionName: Flow<String>
        get() = preferencesRepository.appPreferencesFlow.map { pref -> pref.appVersion }
}