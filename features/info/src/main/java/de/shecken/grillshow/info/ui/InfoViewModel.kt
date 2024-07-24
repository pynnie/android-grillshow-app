package de.shecken.grillshow.info.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.shecken.grillshow.info.R
import de.shecken.grillshow.info.interactor.InfoInteractor
import de.shecken.grillshow.legal.LegalScreenType
import de.shecken.grillshow.navigation.InfoRouter
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

    fun onTermsClick() = router.openLegalPage(LegalScreenType.TERMS)

    fun onPrivacyClick() = router.openLegalPage(LegalScreenType.PRIVACY)

    fun onLicensesClick() = router.openLicenses()
}