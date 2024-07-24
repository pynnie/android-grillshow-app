package de.shecken.grillshow.repository.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    /**
     * [Flow] containing all app-side preferences
     */
    val appPreferencesFlow: Flow<AppPreferences>

    /**
     * Update the flat for app initialization
     *
     * @param initCompleted new value for the flag
     */
    suspend fun updateInitCompleted(initCompleted: Boolean)

    /**
     * Update the app version name
     *
     * @param version the new version name
     */
    suspend fun updateAppVersion(version: String)
}