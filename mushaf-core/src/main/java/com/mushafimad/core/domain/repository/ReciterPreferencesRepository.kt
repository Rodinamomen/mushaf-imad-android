package com.mushafimad.core.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing reciter preferences
 * Public API - exposed to library consumers
 */
interface ReciterPreferencesRepository {

    /**
     * Observe the selected reciter ID
     */
    fun getSelectedReciterIdFlow(): Flow<Int>

    /**
     * Get the selected reciter ID
     */
    suspend fun getSelectedReciterId(): Int

    /**
     * Set the selected reciter ID
     * @param reciterId The reciter ID to save
     */
    suspend fun setSelectedReciterId(reciterId: Int)

    /**
     * Observe the selected playback speed
     */
    fun getPlaybackSpeedFlow(): Flow<Float>

    /**
     * Get the selected playback speed
     */
    suspend fun getPlaybackSpeed(): Float

    /**
     * Set the playback speed
     * @param speed Playback speed (0.5 - 2.0)
     */
    suspend fun setPlaybackSpeed(speed: Float)

    /**
     * Observe repeat mode
     */
    fun getRepeatModeFlow(): Flow<Boolean>

    /**
     * Get repeat mode
     */
    suspend fun getRepeatMode(): Boolean

    /**
     * Set repeat mode
     * @param enabled Whether repeat is enabled
     */
    suspend fun setRepeatMode(enabled: Boolean)
}
