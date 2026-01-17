package com.mushafimad.core.di

import com.mushafimad.core.data.repository.*
import com.mushafimad.core.domain.repository.*
import com.mushafimad.core.internal.ServiceRegistry
import org.koin.dsl.module

/**
 * Koin module for core library dependencies
 * Provides repositories as singletons with constructor injection
 *
 * Architecture:
 * - Koin manages repository lifecycle and dependency injection
 * - ServiceRegistry provides low-level infrastructure services (Realm, Context, Audio services)
 * - Repositories are simple classes with constructor injection
 */
val coreModule = module {
    // Infrastructure services from ServiceRegistry
    single { ServiceRegistry.getContext() }
    single { ServiceRegistry.getRealmService() }
    single { ServiceRegistry.getSharedPreferences() }
    single { ServiceRegistry.getChaptersCache() }
    single { ServiceRegistry.getQuranCacheService() }
    single { ServiceRegistry.getReciterService() }
    single { ServiceRegistry.getAyahTimingService() }
    single { ServiceRegistry.getMediaSessionManager() }

    // Quran Data Repositories
    single<QuranRepository> {
        DefaultQuranRepository(
            realmService = get(),
            chaptersDataCache = get(),
            quranDataCacheService = get()
        )
    }
    single<ChapterRepository> {
        DefaultChapterRepository(
            realmService = get(),
            chaptersDataCache = get()
        )
    }
    single<PageRepository> {
        DefaultPageRepository(
            realmService = get(),
            cacheService = get()
        )
    }
    single<VerseRepository> {
        DefaultVerseRepository(
            realmService = get(),
            cacheService = get()
        )
    }

    // User Data Repositories
    single<BookmarkRepository> {
        DefaultBookmarkRepository(get())
    }
    single<ReadingHistoryRepository> {
        DefaultReadingHistoryRepository(get())
    }
    single<SearchHistoryRepository> {
        DefaultSearchHistoryRepository(get())
    }

    // Audio Repository
    single<AudioRepository> {
        DefaultAudioRepository(get(), get(), get())
    }

    // Preferences Repositories
    single<PreferencesRepository> {
        DefaultPreferencesRepository(get())
    }
    single<ReciterPreferencesRepository> {
        DefaultReciterPreferencesRepository(get())
    }
    single<ThemeRepository> {
        DefaultThemeRepository(get())
    }

    // Data Export Repository
    single<DataExportRepository> {
        DefaultDataExportRepository(get(), get(), get(), get(), get(), get())
    }
}
