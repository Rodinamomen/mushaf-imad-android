package com.mushafimad.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * MushafTheme - Main theme composable for the Mushaf library
 * Combines reading themes, color schemes, and typography
 *
 * Usage:
 * ```
 * MushafTheme(
 *     readingTheme = ReadingTheme.COMFORTABLE,
 *     colorScheme = ColorSchemeType.DEFAULT
 * ) {
 *     // Your composable content
 * }
 * ```
 */
@Composable
fun MushafTheme(
    readingTheme: ReadingTheme = ReadingTheme.COMFORTABLE,
    colorScheme: ColorSchemeType = ColorSchemeType.DEFAULT,
    content: @Composable () -> Unit
) {
    val materialColorScheme = when (colorScheme) {
        ColorSchemeType.DEFAULT -> createDefaultColorScheme(readingTheme.isDark)
        ColorSchemeType.WARM -> createWarmColorScheme(readingTheme.isDark)
        ColorSchemeType.COOL -> createCoolColorScheme(readingTheme.isDark)
        ColorSchemeType.SEPIA -> createSepiaColorScheme(readingTheme.isDark)
    }

    // Provide custom reading theme colors
    CompositionLocalProvider(
        LocalReadingTheme provides readingTheme,
        LocalMushafColors provides if (readingTheme.isDark) {
            MushafColors.darkColors()
        } else {
            MushafColors.lightColors()
        }
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = MushafMaterialTypography,
            content = content
        )
    }
}

/**
 * Color scheme type selector
 * Public API - exposed to library consumers
 */
enum class ColorSchemeType {
    DEFAULT,
    WARM,
    COOL,
    SEPIA
}

/**
 * Create Default ColorScheme
 */
private fun createDefaultColorScheme(isDark: Boolean): ColorScheme {
    return ColorScheme(
        primary = ColorSchemes.Default.primary,
        onPrimary = ColorSchemes.Default.onPrimary,
        primaryContainer = ColorSchemes.Default.primaryContainer,
        onPrimaryContainer = ColorSchemes.Default.onPrimaryContainer,

        secondary = ColorSchemes.Default.secondary,
        onSecondary = ColorSchemes.Default.onSecondary,
        secondaryContainer = ColorSchemes.Default.secondaryContainer,
        onSecondaryContainer = ColorSchemes.Default.onSecondaryContainer,

        tertiary = ColorSchemes.Default.tertiary,
        onTertiary = ColorSchemes.Default.onTertiary,
        tertiaryContainer = ColorSchemes.Default.tertiaryContainer,
        onTertiaryContainer = ColorSchemes.Default.onTertiaryContainer,

        error = ColorSchemes.Default.error,
        onError = ColorSchemes.Default.onError,
        errorContainer = ColorSchemes.Default.errorContainer,
        onErrorContainer = ColorSchemes.Default.onErrorContainer,

        background = ColorSchemes.Default.background,
        onBackground = ColorSchemes.Default.onBackground,
        surface = ColorSchemes.Default.surface,
        onSurface = ColorSchemes.Default.onSurface,

        surfaceVariant = ColorSchemes.Default.surfaceVariant,
        onSurfaceVariant = ColorSchemes.Default.onSurfaceVariant,
        surfaceTint = ColorSchemes.Default.primary,
        outline = ColorSchemes.Default.outline,
        outlineVariant = ColorSchemes.Default.outline,

        scrim = Color.Black.copy(alpha = 0.32f),
        inverseSurface = if (isDark) Color(0xFFE0E0E0) else Color(0xFF2C2C2C),
        inverseOnSurface = if (isDark) Color(0xFF2C2C2C) else Color(0xFFE0E0E0),
        inversePrimary = ColorSchemes.Default.primary,

        surfaceDim = ColorSchemes.Default.surface,
        surfaceBright = ColorSchemes.Default.surface,
        surfaceContainerLowest = ColorSchemes.Default.surface,
        surfaceContainerLow = ColorSchemes.Default.surface,
        surfaceContainer = ColorSchemes.Default.surfaceVariant,
        surfaceContainerHigh = ColorSchemes.Default.surfaceVariant,
        surfaceContainerHighest = ColorSchemes.Default.surfaceVariant
    )
}

/**
 * Create Warm ColorScheme
 */
private fun createWarmColorScheme(isDark: Boolean): ColorScheme {
    return ColorScheme(
        primary = ColorSchemes.Warm.primary,
        onPrimary = ColorSchemes.Warm.onPrimary,
        primaryContainer = ColorSchemes.Warm.primaryContainer,
        onPrimaryContainer = ColorSchemes.Warm.onPrimaryContainer,

        secondary = ColorSchemes.Warm.secondary,
        onSecondary = ColorSchemes.Warm.onSecondary,
        secondaryContainer = ColorSchemes.Warm.secondaryContainer,
        onSecondaryContainer = ColorSchemes.Warm.onSecondaryContainer,

        tertiary = ColorSchemes.Warm.tertiary,
        onTertiary = ColorSchemes.Warm.onTertiary,
        tertiaryContainer = ColorSchemes.Warm.tertiaryContainer,
        onTertiaryContainer = ColorSchemes.Warm.onTertiaryContainer,

        error = ColorSchemes.Warm.error,
        onError = ColorSchemes.Warm.onError,
        errorContainer = ColorSchemes.Warm.errorContainer,
        onErrorContainer = ColorSchemes.Warm.onErrorContainer,

        background = ColorSchemes.Warm.background,
        onBackground = ColorSchemes.Warm.onBackground,
        surface = ColorSchemes.Warm.surface,
        onSurface = ColorSchemes.Warm.onSurface,

        surfaceVariant = ColorSchemes.Warm.surfaceVariant,
        onSurfaceVariant = ColorSchemes.Warm.onSurfaceVariant,
        surfaceTint = ColorSchemes.Warm.primary,
        outline = ColorSchemes.Warm.outline,
        outlineVariant = ColorSchemes.Warm.outline,

        scrim = Color.Black.copy(alpha = 0.32f),
        inverseSurface = if (isDark) Color(0xFFE0E0E0) else Color(0xFF2C2C2C),
        inverseOnSurface = if (isDark) Color(0xFF2C2C2C) else Color(0xFFE0E0E0),
        inversePrimary = ColorSchemes.Warm.primary,

        surfaceDim = ColorSchemes.Warm.surface,
        surfaceBright = ColorSchemes.Warm.surface,
        surfaceContainerLowest = ColorSchemes.Warm.surface,
        surfaceContainerLow = ColorSchemes.Warm.surface,
        surfaceContainer = ColorSchemes.Warm.surfaceVariant,
        surfaceContainerHigh = ColorSchemes.Warm.surfaceVariant,
        surfaceContainerHighest = ColorSchemes.Warm.surfaceVariant
    )
}

/**
 * Create Cool ColorScheme
 */
private fun createCoolColorScheme(isDark: Boolean): ColorScheme {
    return ColorScheme(
        primary = ColorSchemes.Cool.primary,
        onPrimary = ColorSchemes.Cool.onPrimary,
        primaryContainer = ColorSchemes.Cool.primaryContainer,
        onPrimaryContainer = ColorSchemes.Cool.onPrimaryContainer,

        secondary = ColorSchemes.Cool.secondary,
        onSecondary = ColorSchemes.Cool.onSecondary,
        secondaryContainer = ColorSchemes.Cool.secondaryContainer,
        onSecondaryContainer = ColorSchemes.Cool.onSecondaryContainer,

        tertiary = ColorSchemes.Cool.tertiary,
        onTertiary = ColorSchemes.Cool.onTertiary,
        tertiaryContainer = ColorSchemes.Cool.tertiaryContainer,
        onTertiaryContainer = ColorSchemes.Cool.onTertiaryContainer,

        error = ColorSchemes.Cool.error,
        onError = ColorSchemes.Cool.onError,
        errorContainer = ColorSchemes.Cool.errorContainer,
        onErrorContainer = ColorSchemes.Cool.onErrorContainer,

        background = ColorSchemes.Cool.background,
        onBackground = ColorSchemes.Cool.onBackground,
        surface = ColorSchemes.Cool.surface,
        onSurface = ColorSchemes.Cool.onSurface,

        surfaceVariant = ColorSchemes.Cool.surfaceVariant,
        onSurfaceVariant = ColorSchemes.Cool.onSurfaceVariant,
        surfaceTint = ColorSchemes.Cool.primary,
        outline = ColorSchemes.Cool.outline,
        outlineVariant = ColorSchemes.Cool.outline,

        scrim = Color.Black.copy(alpha = 0.32f),
        inverseSurface = if (isDark) Color(0xFFE0E0E0) else Color(0xFF2C2C2C),
        inverseOnSurface = if (isDark) Color(0xFF2C2C2C) else Color(0xFFE0E0E0),
        inversePrimary = ColorSchemes.Cool.primary,

        surfaceDim = ColorSchemes.Cool.surface,
        surfaceBright = ColorSchemes.Cool.surface,
        surfaceContainerLowest = ColorSchemes.Cool.surface,
        surfaceContainerLow = ColorSchemes.Cool.surface,
        surfaceContainer = ColorSchemes.Cool.surfaceVariant,
        surfaceContainerHigh = ColorSchemes.Cool.surfaceVariant,
        surfaceContainerHighest = ColorSchemes.Cool.surfaceVariant
    )
}

/**
 * Create Sepia ColorScheme
 */
private fun createSepiaColorScheme(isDark: Boolean): ColorScheme {
    return ColorScheme(
        primary = ColorSchemes.Sepia.primary,
        onPrimary = ColorSchemes.Sepia.onPrimary,
        primaryContainer = ColorSchemes.Sepia.primaryContainer,
        onPrimaryContainer = ColorSchemes.Sepia.onPrimaryContainer,

        secondary = ColorSchemes.Sepia.secondary,
        onSecondary = ColorSchemes.Sepia.onSecondary,
        secondaryContainer = ColorSchemes.Sepia.secondaryContainer,
        onSecondaryContainer = ColorSchemes.Sepia.onSecondaryContainer,

        tertiary = ColorSchemes.Sepia.tertiary,
        onTertiary = ColorSchemes.Sepia.onTertiary,
        tertiaryContainer = ColorSchemes.Sepia.tertiaryContainer,
        onTertiaryContainer = ColorSchemes.Sepia.onTertiaryContainer,

        error = ColorSchemes.Sepia.error,
        onError = ColorSchemes.Sepia.onError,
        errorContainer = ColorSchemes.Sepia.errorContainer,
        onErrorContainer = ColorSchemes.Sepia.onErrorContainer,

        background = ColorSchemes.Sepia.background,
        onBackground = ColorSchemes.Sepia.onBackground,
        surface = ColorSchemes.Sepia.surface,
        onSurface = ColorSchemes.Sepia.onSurface,

        surfaceVariant = ColorSchemes.Sepia.surfaceVariant,
        onSurfaceVariant = ColorSchemes.Sepia.onSurfaceVariant,
        surfaceTint = ColorSchemes.Sepia.primary,
        outline = ColorSchemes.Sepia.outline,
        outlineVariant = ColorSchemes.Sepia.outline,

        scrim = Color.Black.copy(alpha = 0.32f),
        inverseSurface = if (isDark) Color(0xFFE0E0E0) else Color(0xFF2C2C2C),
        inverseOnSurface = if (isDark) Color(0xFF2C2C2C) else Color(0xFFE0E0E0),
        inversePrimary = ColorSchemes.Sepia.primary,

        surfaceDim = ColorSchemes.Sepia.surface,
        surfaceBright = ColorSchemes.Sepia.surface,
        surfaceContainerLowest = ColorSchemes.Sepia.surface,
        surfaceContainerLow = ColorSchemes.Sepia.surface,
        surfaceContainer = ColorSchemes.Sepia.surfaceVariant,
        surfaceContainerHigh = ColorSchemes.Sepia.surfaceVariant,
        surfaceContainerHighest = ColorSchemes.Sepia.surfaceVariant
    )
}

/**
 * Composition local for current reading theme
 */
val LocalReadingTheme = staticCompositionLocalOf { ReadingTheme.COMFORTABLE }

/**
 * Composition local for Mushaf-specific colors
 */
val LocalMushafColors = staticCompositionLocalOf { MushafColors.lightColors() }

/**
 * Extension of MushafColors to provide theme-aware color sets
 */
private fun MushafColors.lightColors(): MushafColorSet {
    return MushafColorSet(
        verseHighlight = MushafColors.verseHighlightLight,
        selection = MushafColors.selectionLight,
        chapterHeaderBackground = MushafColors.chapterHeaderBackground,
        bismillahTint = MushafColors.bismillahTint,
        divider = MushafColors.divider,
        scrim = MushafColors.scrimLight
    )
}

private fun MushafColors.darkColors(): MushafColorSet {
    return MushafColorSet(
        verseHighlight = MushafColors.verseHighlightDark,
        selection = MushafColors.selectionDark,
        chapterHeaderBackground = MushafColors.chapterHeaderBackgroundDark,
        bismillahTint = MushafColors.bismillahTintDark,
        divider = MushafColors.dividerDark,
        scrim = MushafColors.scrimDark
    )
}

/**
 * Set of Mushaf-specific colors that adapt to theme
 */
data class MushafColorSet(
    val verseHighlight: Color,
    val selection: Color,
    val chapterHeaderBackground: Color,
    val bismillahTint: Color,
    val divider: Color,
    val scrim: Color
)

/**
 * Access current reading theme
 */
val MaterialTheme.readingTheme: ReadingTheme
    @Composable
    get() = LocalReadingTheme.current

/**
 * Access Mushaf-specific colors
 */
val MaterialTheme.mushafColors: MushafColorSet
    @Composable
    get() = LocalMushafColors.current
