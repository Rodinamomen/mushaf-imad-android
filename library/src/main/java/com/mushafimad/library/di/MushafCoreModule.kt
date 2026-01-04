package com.mushafimad.library.di

import android.content.Context
import com.mushafimad.library.data.repository.RealmService
import com.mushafimad.library.data.repository.RealmServiceImpl
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
}
