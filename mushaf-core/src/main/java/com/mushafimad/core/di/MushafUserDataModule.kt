package com.mushafimad.core.di

import com.mushafimad.core.domain.repository.BookmarkRepository
import com.mushafimad.core.domain.repository.DataExportRepository
import com.mushafimad.core.domain.repository.PreferencesRepository
import com.mushafimad.core.domain.repository.ReadingHistoryRepository
import com.mushafimad.core.domain.repository.ReciterPreferencesRepository
import com.mushafimad.core.domain.repository.SearchHistoryRepository
import com.mushafimad.core.domain.repository.ThemeRepository
import com.mushafimad.core.data.repository.DataExportRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing user data management dependencies
 * Includes data export/import and cross-repository operations
 */
@Module
@InstallIn(SingletonComponent::class)
object MushafUserDataModule {

    @Provides
    @Singleton
    internal fun provideDataExportRepository(
        bookmarkRepository: BookmarkRepository,
        readingHistoryRepository: ReadingHistoryRepository,
        searchHistoryRepository: SearchHistoryRepository,
        preferencesRepository: PreferencesRepository,
        reciterPreferencesRepository: ReciterPreferencesRepository,
        themeRepository: ThemeRepository
    ): DataExportRepository {
        return DataExportRepositoryImpl(
            bookmarkRepository,
            readingHistoryRepository,
            searchHistoryRepository,
            preferencesRepository,
            reciterPreferencesRepository,
            themeRepository
        )
    }
}
