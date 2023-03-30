package de.shecken.grillshow.shared.provider

import androidx.annotation.StringRes

interface StringProvider {

    /**
     * Provide the [String] for a given resource ID
     */
    fun provideString(@StringRes stringResId: Int): String
}