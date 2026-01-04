package com.mushafimad.library.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module providing user preferences and settings dependencies
 * Includes DataStore preferences, theme settings, and user configurations
 */
@Module
@InstallIn(SingletonComponent::class)
object MushafPreferencesModule {
    // Preferences dependencies will be added in later weeks
    // - DataStore instance
    // - SettingsRepository
    // - ThemeManager
}
