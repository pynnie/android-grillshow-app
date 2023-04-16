package de.shecken.grillshow.info.vo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class InfoItemVo(
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int,
    @DrawableRes val iconRes: Int
)
