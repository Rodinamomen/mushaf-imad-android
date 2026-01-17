package com.mushafimad.library.data.repository

import com.mushafimad.library.data.audio.AudioPlayerState
import com.mushafimad.library.data.audio.AyahTimingService
import com.mushafimad.library.data.audio.MediaSessionManager
import com.mushafimad.library.data.audio.ReciterDataProvider
import com.mushafimad.library.domain.models.AyahTiming
import com.mushafimad.library.domain.models.ReciterInfo
import com.mushafimad.library.domain.repository.AudioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of AudioRepository
 * Internal implementation - not exposed in public API
 */
@Singleton
internal class AudioRepositoryImpl @Inject constructor(
    private val mediaSessionManager: MediaSessionManager,
    private val ayahTimingService: AyahTimingService
) : AudioRepository {

    init {
        // Initialize MediaSessionManager
        mediaSessionManager.initialize()
    }

    // Reciter operations
    override fun getAllReciters(): List<ReciterInfo> {
        return ReciterDataProvider.allReciters
    }

    override fun getReciterById(reciterId: Int): ReciterInfo? {
        return ReciterDataProvider.getReciterById(reciterId)
    }

    override fun searchReciters(query: String, languageCode: String): List<ReciterInfo> {
        return ReciterDataProvider.searchReciters(query, languageCode)
    }

    override fun getHafsReciters(): List<ReciterInfo> {
        return ReciterDataProvider.getHafsReciters()
    }

    override fun getDefaultReciter(): ReciterInfo {
        return ReciterDataProvider.getDefaultReciter()
    }

    // Playback control
    override fun getPlayerStateFlow(): Flow<AudioPlayerState> {
        return mediaSessionManager.playerState
    }

    override fun loadChapter(chapterNumber: Int, reciterId: Int, autoPlay: Boolean) {
        mediaSessionManager.loadChapter(chapterNumber, reciterId, autoPlay)
    }

    override fun play() {
        mediaSessionManager.play()
    }

    override fun pause() {
        mediaSessionManager.pause()
    }

    override fun stop() {
        mediaSessionManager.stop()
    }

    override fun seekTo(positionMs: Long) {
        mediaSessionManager.seekTo(positionMs)
    }

    override fun setPlaybackSpeed(speed: Float) {
        mediaSessionManager.setPlaybackSpeed(speed)
    }

    override fun setRepeatMode(enabled: Boolean) {
        // Note: MediaSessionManager uses toggleRepeat(), so we need to check current state
        val currentState = mediaSessionManager.playerState.value.isRepeatEnabled
        if (currentState != enabled) {
            mediaSessionManager.toggleRepeat()
        }
    }

    override fun isRepeatEnabled(): Boolean {
        return mediaSessionManager.playerState.value.isRepeatEnabled
    }

    override fun getCurrentPosition(): Long {
        return mediaSessionManager.getCurrentPosition()
    }

    override fun getDuration(): Long {
        return mediaSessionManager.getDuration()
    }

    override fun isPlaying(): Boolean {
        return mediaSessionManager.isPlaying()
    }

    // Timing operations
    override fun getAyahTiming(reciterId: Int, chapterNumber: Int, ayahNumber: Int): AyahTiming? {
        return ayahTimingService.getTiming(reciterId, chapterNumber, ayahNumber)
    }

    override fun getCurrentVerse(reciterId: Int, chapterNumber: Int, currentTimeMs: Int): Int? {
        return ayahTimingService.getCurrentVerse(reciterId, chapterNumber, currentTimeMs)
    }

    override fun getChapterTimings(reciterId: Int, chapterNumber: Int): List<AyahTiming> {
        return ayahTimingService.getChapterTimings(reciterId, chapterNumber)
    }

    override fun hasTimingForReciter(reciterId: Int): Boolean {
        return ayahTimingService.hasTimingForReciter(reciterId)
    }

    override suspend fun preloadTiming(reciterId: Int) {
        ayahTimingService.preloadTiming(reciterId)
    }

    // Lifecycle
    override fun release() {
        mediaSessionManager.release()
    }
}
