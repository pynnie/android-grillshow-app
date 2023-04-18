package de.shecken.grillshow.info.navigation

import androidx.annotation.StringRes

interface InfoRouter {

    fun openURL(url: String)

    fun openURL(@StringRes urlRes: Int)

    fun openEmail()

    fun openLicenses()
}