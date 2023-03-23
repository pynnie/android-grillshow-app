package de.shecken.grillshow.video.search

import androidx.lifecycle.ViewModel
import de.shecken.grillshow.video.DashboardRouter

internal class SearchViewModel(private val dashboardRouter: DashboardRouter) : ViewModel() {

    fun onBackButtonClick() = dashboardRouter.finishDetails()
}
