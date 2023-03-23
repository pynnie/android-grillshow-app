package de.shecken.grillshow.video.dashboard

import de.shecken.grillshow.repository.video.PlayList

internal sealed class DashboardSceenState {

    object Loading : DashboardSceenState()

    data class Success(val playlistItems: List<PlayList>) : DashboardSceenState()

    object Failure : DashboardSceenState()
}
