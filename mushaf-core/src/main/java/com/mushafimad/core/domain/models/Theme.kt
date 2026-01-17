package com.mushafimad.core.domain.models

/**
 * Theme mode for the application
 * Public API - exposed to library consumers
 */
enum class ThemeMode {
    LIGHT,
    DARK,
    SYSTEM  // Follow system theme
}

/**
 * Color scheme options
 * Public API - exposed to library consumers
 */
enum class ColorScheme {
    DEFAULT,
    WARM,
    COOL,
    SEPIA
}

/**
 * Theme configuration
 * Public API - exposed to library consumers
 */
data class ThemeConfig(
    val mode: ThemeMode = ThemeMode.SYSTEM,
    val colorScheme: ColorScheme = ColorScheme.DEFAULT,
    val useAmoled: Boolean = false  // Pure black for AMOLED screens
)
