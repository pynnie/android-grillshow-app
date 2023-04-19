package de.shecken.grillshow.legal

import androidx.annotation.StringRes
import de.shecken.grillshow.info.R

enum class LegalScreenType(@StringRes val titleRes: Int, val url: String) {
    PRIVACY(titleRes = R.string.info_privacy, url = "file:///android_asset/privacy.html"),
    TERMS(titleRes = R.string.info_terms, url = "file:///android_asset/terms.html")
}
