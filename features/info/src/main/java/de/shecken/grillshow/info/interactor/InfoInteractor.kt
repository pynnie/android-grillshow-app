package de.shecken.grillshow.info.interactor

import kotlinx.coroutines.flow.Flow

interface InfoInteractor {

    /**
     * Current version name as flow
     */
    val versionName: Flow<String>
}