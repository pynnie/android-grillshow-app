package de.shecken.grillshow.navigation

import androidx.annotation.StringRes
import de.shecken.grillshow.legal.LegalScreenType

interface InfoRouter {

    fun openURL(url: String)

    fun openURL(@StringRes urlRes: Int)

    fun openEmail()

    fun openLicenses()

    fun openLegalPage(legalType: LegalScreenType)

    fun goBack()
}