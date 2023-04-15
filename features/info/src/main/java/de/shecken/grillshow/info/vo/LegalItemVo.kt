package de.shecken.grillshow.info.vo

data class LegalItemVo(
    val title: String,
    val iconRes: Int,
    val onClick: () -> Unit
)
