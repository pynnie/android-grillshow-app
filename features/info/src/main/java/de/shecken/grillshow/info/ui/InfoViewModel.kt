package de.shecken.grillshow.info.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.info.interactor.InfoInteractor
import de.shecken.grillshow.info.navigation.InfoRouter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class InfoViewModel(interactor: InfoInteractor, private val router: InfoRouter) : ViewModel() {

    private val _versionName = interactor.versionName
    val versionName =
        _versionName.stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, "")

    fun onSocialMediaLinkClicked(url: String) {
        router.openURL(url)
    }

    fun onContactClick() = router.openEmail()
}