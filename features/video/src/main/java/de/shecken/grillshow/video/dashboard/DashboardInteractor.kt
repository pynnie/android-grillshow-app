package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.video.PlayList
import kotlinx.coroutines.flow.Flow

/**
 * Interactor for the [DashboardScreen]
 */
internal interface DashboardInteractor {

    /**
     * @return A [Flow] emitting all changes of [PlayList]
     */
    suspend fun getVideos(): Flow<List<PlayList>>
}
