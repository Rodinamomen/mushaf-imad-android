package com.mushafimad.core.di

import android.content.Context
import com.mushafimad.core.data.cache.ChaptersDataCache
import com.mushafimad.core.data.cache.QuranDataCacheService
import com.mushafimad.core.data.repository.*
import com.mushafimad.core.domain.repository.BookmarkRepository
import com.mushafimad.core.domain.repository.ChapterRepository
import com.mushafimad.core.domain.repository.PageRepository
import com.mushafimad.core.domain.repository.QuranRepository
import com.mushafimad.core.domain.repository.ReadingHistoryRepository
import com.mushafimad.core.domain.repository.SearchHistoryRepository
import com.mushafimad.core.domain.repository.VerseRepository
import com.mushafimad.core.internal.LibraryInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing core dependencies for the Mushaf library
 * Includes database access, caching, and core services
 */
@Module
@InstallIn(SingletonComponent::class)
object MushafCoreModule {

    @Provides
    @Singleton
    internal fun provideRealmService(
        @ApplicationContext context: Context
    ): RealmService {
        return RealmServiceImpl(context)
    }

    @Provides
    @Singleton
    internal fun provideChaptersDataCache(
        realmService: RealmService
    ): ChaptersDataCache {
        return ChaptersDataCache(realmService)
    }

    @Provides
    @Singleton
    internal fun provideQuranDataCacheService(
        realmService: RealmService
    ): QuranDataCacheService {
        return QuranDataCacheService(realmService)
    }

    // Repository Providers

    @Provides
    @Singleton
    internal fun provideChapterRepository(
        realmService: RealmService,
        chaptersDataCache: ChaptersDataCache
    ): ChapterRepository {
        return ChapterRepositoryImpl(realmService, chaptersDataCache)
    }

    @Provides
    @Singleton
    internal fun providePageRepository(
        realmService: RealmService,
        cacheService: QuranDataCacheService
    ): PageRepository {
        return PageRepositoryImpl(realmService, cacheService)
    }

    @Provides
    @Singleton
    internal fun provideVerseRepository(
        realmService: RealmService,
        cacheService: QuranDataCacheService
    ): VerseRepository {
        return VerseRepositoryImpl(realmService, cacheService)
    }

    @Provides
    @Singleton
    internal fun provideQuranRepository(
        realmService: RealmService,
        chaptersDataCache: ChaptersDataCache,
        quranDataCacheService: QuranDataCacheService
    ): QuranRepository {
        return QuranRepositoryImpl(realmService, chaptersDataCache, quranDataCacheService)
    }

    @Provides
    @Singleton
    internal fun provideBookmarkRepository(
        realmService: RealmService
    ): BookmarkRepository {
        return BookmarkRepositoryImpl(realmService)
    }

    @Provides
    @Singleton
    internal fun provideReadingHistoryRepository(
        realmService: RealmService
    ): ReadingHistoryRepository {
        return ReadingHistoryRepositoryImpl(realmService)
    }

    @Provides
    @Singleton
    internal fun provideSearchHistoryRepository(
        realmService: RealmService
    ): SearchHistoryRepository {
        return SearchHistoryRepositoryImpl(realmService)
    }
}
