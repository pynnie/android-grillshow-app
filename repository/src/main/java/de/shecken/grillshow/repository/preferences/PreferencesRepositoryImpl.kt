package de.shecken.grillshow.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {

    private object PreferencesKeys {
        val INIT_COMPLETED = booleanPreferencesKey(KEY_INIT_COMPLETE)
    }

    override val appPreferencesFlow: Flow<AppPreferences> = dataStore.data
        .map { preferences ->
            val isInitCompleted = preferences[PreferencesKeys.INIT_COMPLETED] ?: false
            AppPreferences(isInitCompleted)
        }

    override suspend fun updateInitCompleted(initCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.INIT_COMPLETED] = initCompleted
        }
    }

    companion object {
        private const val KEY_INIT_COMPLETE = "is_init_complete"
    }
}