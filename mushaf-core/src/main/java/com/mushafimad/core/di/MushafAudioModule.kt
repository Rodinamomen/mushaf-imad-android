package com.mushafimad.core.di

import com.mushafimad.core.data.audio.AyahTimingService
import com.mushafimad.core.data.audio.MediaSessionManager
import com.mushafimad.core.data.audio.ReciterService
import com.mushafimad.core.data.repository.AudioRepositoryImpl
import com.mushafimad.core.domain.repository.AudioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing audio playback dependencies
 * Includes Media3 MediaSession, background playback, and recitation services
 */
@Module
@InstallIn(SingletonComponent::class)
object MushafAudioModule {

    /**
     * Provides AudioRepository for audio playback and reciter management
     */
    @Provides
    @Singleton
    internal fun provideAudioRepository(
        mediaSessionManager: MediaSessionManager,
        ayahTimingService: AyahTimingService,
        reciterService: ReciterService
    ): AudioRepository = AudioRepositoryImpl(mediaSessionManager, ayahTimingService, reciterService)
}
