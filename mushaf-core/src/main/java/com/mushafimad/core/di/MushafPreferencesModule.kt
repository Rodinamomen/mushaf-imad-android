package com.mushafimad.core.di

import android.content.Context
import android.content.SharedPreferences
import com.mushafimad.core.data.repository.PreferencesRepositoryImpl
import com.mushafimad.core.data.repository.ReciterPreferencesRepositoryImpl
import com.mushafimad.core.data.repository.ThemeRepositoryImpl
import com.mushafimad.core.domain.repository.PreferencesRepository
import com.mushafimad.core.domain.repository.ReciterPreferencesRepository
import com.mushafimad.core.domain.repository.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing user preferences and settings dependencies
 * Includes DataStore preferences, theme settings, and user configurations
 */
@Module
@InstallIn(SingletonComponent::class)
object MushafPreferencesModule {

    @Provides
    @Singleton
    internal fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("mushaf_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun providePreferencesRepository(
        @ApplicationContext context: Context
    ): PreferencesRepository {
        return PreferencesRepositoryImpl(context)
    }

    @Provides
    @Singleton
    internal fun provideReciterPreferencesRepository(
        @ApplicationContext context: Context
    ): ReciterPreferencesRepository {
        return ReciterPreferencesRepositoryImpl(context)
    }

    @Provides
    @Singleton
    internal fun provideThemeRepository(
        @ApplicationContext context: Context
    ): ThemeRepository {
        return ThemeRepositoryImpl(context)
    }
}
