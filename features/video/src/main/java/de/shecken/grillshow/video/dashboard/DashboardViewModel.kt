package de.shecken.grillshow.video.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.video.VideoRouter
import de.shecken.grillshow.video.dashboard.DashboardSceenState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

internal class DashboardViewModel(
    private val videoRouter: VideoRouter,
    private val interactor: DashboardInteractor) : ViewModel() {

    private val _dashboardSceenState = MutableStateFlow<DashboardSceenState>(Loading)
    val dashboardSceenState: StateFlow<DashboardSceenState> = _dashboardSceenState

    init {
        viewModelScope.launch {
            try {
                interactor
                    .getVideos()
                    .collect { _dashboardSceenState.value = Success(it) }
            } catch (t: Throwable) {
                _dashboardSceenState.value = Failure
                Timber.e(t)
            }
        }
    }

    fun onScreenClicked() = videoRouter.navigateToDetails()
}
