package de.shecken.grillshow.shared.compose.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColors(
    primary = Color.Blue
)

private val LightColors = lightColors(
    primary = Color.Blue
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = if (isSystemInDarkTheme()) DarkColors else LightColors, content = content)
}
