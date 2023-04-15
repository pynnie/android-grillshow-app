package de.shecken.grillshow.info.vo

data class InfoItemVo(
    val title: String,
    val subtitle: String,
    val onClick: () -> Unit
)
