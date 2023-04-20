package de.shecken.grillshow.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import de.shecken.grillshow.shared.R

enum class ContentScreenId(
    @StringRes val labelTextRes: Int,
    @DrawableRes val iconRes: Int,
) {
    DASHBOARD(
        labelTextRes = R.string.navigation_dashboard,
        iconRes = R.drawable.ic_grill,
    ),
    FAVORITES(
        labelTextRes = R.string.navigation_favorites,
        iconRes = R.drawable.ic_favorite,
    ),
    INFO(
        labelTextRes = R.string.navigation_info,
        iconRes = R.drawable.ic_more,
    )
}