package com.mushafimad.library.di

import com.mushafimad.library.domain.repository.BookmarkRepository
import com.mushafimad.library.domain.repository.DataExportRepository
import com.mushafimad.library.domain.repository.PreferencesRepository
import com.mushafimad.library.domain.repository.ReadingHistoryRepository
import com.mushafimad.library.domain.repository.ReciterPreferencesRepository
import com.mushafimad.library.domain.repository.SearchHistoryRepository
import com.mushafimad.library.domain.repository.ThemeRepository
import com.mushafimad.library.data.repository.DataExportRepositoryImpl
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
