package de.shecken.grillshow.info.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.info.R
import de.shecken.grillshow.info.interactor.InfoInteractor
import de.shecken.grillshow.info.navigation.InfoRouter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class InfoViewModel(interactor: InfoInteractor, private val router: InfoRouter) : ViewModel() {

    private val _versionName = interactor.versionName
    val versionName =
        _versionName.stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, "")

    fun onSocialMediaLinkClicked(@StringRes url: Int) {
        router.openURL(url)
    }

    fun onContactClick() = router.openEmail()

    fun onDevInfoClick() = router.openURL(R.string.github_url)

    fun onTermsClick() = router.openURL(R.string.terms_url)

    fun onPrivacyClick() = router.openURL(R.string.privacy_url)

    fun onLicensesClick() = router.openLicenses()
}