package com.mushafimad.library.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module providing audio playback dependencies
 * Includes Media3 ExoPlayer, audio session management, and recitation services
 */
@Module
@InstallIn(SingletonComponent::class)
object MushafAudioModule {
    // Audio dependencies will be added in later weeks
    // - ExoPlayer instance
    // - AudioSessionManager
    // - RecitationService
}
