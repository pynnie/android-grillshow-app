package de.shecken.grillshow.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(
        R.font.montserrat_extralight_italic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.montserrat_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.montserrat_black, weight = FontWeight.Black),
    Font(R.font.montserrat_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.montserrat_extrabold, weight = FontWeight.ExtraBold),
    Font(
        R.font.montserrat_extrabold_italic,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Italic
    ),
    Font(R.font.montserrat_extralight, weight = FontWeight.ExtraLight),
    Font(
        R.font.montserrat_extralight_italic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(R.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(R.font.montserrat_semibold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.montserrat_thin, weight = FontWeight.Thin),
    Font(R.font.montserrat_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic)
)

@Composable
fun GrillshowTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColors,
        content = content,
        typography = with(MaterialTheme.typography) {
            Typography(
                displayLarge = displayLarge.copy(fontFamily = MontserratFontFamily),
                displayMedium = displayMedium.copy(fontFamily = MontserratFontFamily),
                displaySmall = displaySmall.copy(fontFamily = MontserratFontFamily),
                headlineLarge = headlineLarge.copy(fontFamily = MontserratFontFamily),
                headlineMedium = headlineMedium.copy(fontFamily = MontserratFontFamily),
                headlineSmall = headlineSmall.copy(fontFamily = MontserratFontFamily),
                titleLarge = titleLarge.copy(fontFamily = MontserratFontFamily),
                titleMedium = titleMedium.copy(fontFamily = MontserratFontFamily),
                titleSmall = titleSmall.copy(fontFamily = MontserratFontFamily),
                bodyLarge = bodyLarge.copy(fontFamily = MontserratFontFamily),
                bodyMedium = bodyMedium.copy(fontFamily = MontserratFontFamily),
                bodySmall = bodySmall.copy(fontFamily = MontserratFontFamily),
                labelLarge = labelLarge.copy(fontFamily = MontserratFontFamily),
                labelMedium = labelMedium.copy(fontFamily = MontserratFontFamily),
                labelSmall = labelSmall.copy(fontFamily = MontserratFontFamily)
            )
        }
    )
}