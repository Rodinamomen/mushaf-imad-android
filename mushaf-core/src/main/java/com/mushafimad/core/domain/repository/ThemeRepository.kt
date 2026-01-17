package com.mushafimad.library.domain.repository

import com.mushafimad.library.domain.models.ColorScheme
import com.mushafimad.library.domain.models.ThemeConfig
import com.mushafimad.library.domain.models.ThemeMode
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing theme and appearance settings
 * Public API - exposed to library consumers
 */
interface ThemeRepository {

    /**
     * Observe theme configuration
     */
    fun getThemeConfigFlow(): Flow<ThemeConfig>

    /**
     * Get current theme configuration
     */
    suspend fun getThemeConfig(): ThemeConfig

    /**
     * Set theme mode
     * @param mode The theme mode (LIGHT, DARK, SYSTEM)
     */
    suspend fun setThemeMode(mode: ThemeMode)

    /**
     * Set color scheme
     * @param scheme The color scheme
     */
    suspend fun setColorScheme(scheme: ColorScheme)

    /**
     * Set AMOLED mode (pure black for dark theme)
     * @param enabled Whether to use AMOLED black
     */
    suspend fun setAmoledMode(enabled: Boolean)

    /**
     * Update complete theme configuration
     * @param config The new theme configuration
     */
    suspend fun updateThemeConfig(config: ThemeConfig)
}
