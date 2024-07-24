package de.shecken.grillshow.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {

    private object PreferencesKeys {
        val INIT_COMPLETED = booleanPreferencesKey(KEY_INIT_COMPLETE)
        val APP_VERSION = stringPreferencesKey(KEY_APP_VERSION)
    }

    override val appPreferencesFlow: Flow<AppPreferences> = dataStore.data
        .map { preferences ->
            val isInitCompleted = preferences[PreferencesKeys.INIT_COMPLETED] ?: false
            val appVersion = preferences[PreferencesKeys.APP_VERSION] ?: APP_VERSION_DEFAULT
            AppPreferences(isInitComplete = isInitCompleted, appVersion = appVersion)
        }

    override suspend fun updateInitCompleted(initCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.INIT_COMPLETED] = initCompleted
        }
    }

    override suspend fun updateAppVersion(version: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_VERSION] = version
        }
    }

    companion object {
        private const val KEY_INIT_COMPLETE = "is_init_complete"
        private const val KEY_APP_VERSION = "app_version"

        private const val APP_VERSION_DEFAULT = "1.0"
    }
}