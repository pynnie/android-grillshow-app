package de.shecken.grillshow.video.search

import androidx.lifecycle.ViewModel
import de.shecken.grillshow.video.VideoRouter

internal class SearchViewModel(private val videoRouter: VideoRouter) : ViewModel() {

    fun onBackButtonClick() = videoRouter.finishDetails()
}
