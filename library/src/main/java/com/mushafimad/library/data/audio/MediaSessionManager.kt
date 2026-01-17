package com.mushafimad.library.data.audio

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.mushafimad.library.MushafLibrary
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager for MediaSession connections to AudioPlaybackService
 *
 * This class handles:
 * - Connecting to AudioPlaybackService via MediaController
 * - Sending playback commands (play, pause, stop)
 * - Sending custom commands (load chapter, change reciter, etc.)
 * - Exposing playback state via StateFlow
 */
@Singleton
@OptIn(UnstableApi::class)
class MediaSessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var controllerFuture: ListenableFuture<MediaController>? = null
    private var mediaController: MediaController? = null

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    /**
     * Initialize and connect to AudioPlaybackService
     */
    fun initialize() {
        if (controllerFuture != null) {
            MushafLibrary.logger.info("MediaSessionManager already initialized")
            return
        }

        MushafLibrary.logger.info("Initializing MediaSessionManager")

        val sessionToken = SessionToken(
            context,
            ComponentName(context, AudioPlaybackService::class.java)
        )

        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture?.addListener(
            {
                try {
                    mediaController = controllerFuture?.get()
                    _isConnected.value = true
                    MushafLibrary.logger.info("MediaController connected successfully")
                } catch (e: Exception) {
                    MushafLibrary.logger.error("Failed to connect MediaController", e)
                    _isConnected.value = false
                }
            },
            MoreExecutors.directExecutor()
        )
    }

    /**
     * Load and play a chapter with specified reciter
     */
    fun loadChapter(chapterNumber: Int, reciterId: Int, autoPlay: Boolean = true) {
        scope.launch {
            val args = Bundle().apply {
                putInt("chapter_number", chapterNumber)
                putInt("reciter_id", reciterId)
            }

            mediaController?.sendCustomCommand(
                SessionCommand("LOAD_CHAPTER", args),
                args
            )

            if (autoPlay) {
                mediaController?.play()
            }

            MushafLibrary.logger.info("Loaded chapter $chapterNumber with reciter $reciterId")
        }
    }

    /**
     * Change reciter (keeps current chapter)
     */
    fun changeReciter(reciterId: Int) {
        scope.launch {
            val args = Bundle().apply {
                putInt("reciter_id", reciterId)
            }

            mediaController?.sendCustomCommand(
                SessionCommand("CHANGE_RECITER", args),
                args
            )

            MushafLibrary.logger.info("Changed reciter to $reciterId")
        }
    }

    /**
     * Set playback speed
     */
    fun setPlaybackSpeed(speed: Float) {
        scope.launch {
            val args = Bundle().apply {
                putFloat("speed", speed)
            }

            mediaController?.sendCustomCommand(
                SessionCommand("SET_SPEED", args),
                args
            )

            MushafLibrary.logger.info("Set playback speed to ${speed}x")
        }
    }

    /**
     * Toggle repeat mode
     */
    fun toggleRepeat() {
        scope.launch {
            mediaController?.sendCustomCommand(
                SessionCommand("TOGGLE_REPEAT", Bundle.EMPTY),
                Bundle.EMPTY
            )

            MushafLibrary.logger.info("Toggled repeat mode")
        }
    }

    /**
     * Play
     */
    fun play() {
        mediaController?.play()
        MushafLibrary.logger.info("Play")
    }

    /**
     * Pause
     */
    fun pause() {
        mediaController?.pause()
        MushafLibrary.logger.info("Pause")
    }

    /**
     * Stop
     */
    fun stop() {
        mediaController?.stop()
        MushafLibrary.logger.info("Stop")
    }

    /**
     * Seek to position in milliseconds
     */
    fun seekTo(positionMs: Long) {
        mediaController?.seekTo(positionMs)
        MushafLibrary.logger.info("Seek to ${positionMs}ms")
    }

    /**
     * Check if currently playing
     */
    fun isPlaying(): Boolean {
        return mediaController?.isPlaying ?: false
    }

    /**
     * Get current position in milliseconds
     */
    fun getCurrentPosition(): Long {
        return mediaController?.currentPosition ?: 0L
    }

    /**
     * Get duration in milliseconds
     */
    fun getDuration(): Long {
        return mediaController?.duration?.let { if (it < 0) 0 else it } ?: 0L
    }

    /**
     * Release resources
     */
    fun release() {
        MushafLibrary.logger.info("Releasing MediaSessionManager")

        mediaController?.release()
        mediaController = null

        controllerFuture?.let { MediaController.releaseFuture(it) }
        controllerFuture = null

        _isConnected.value = false
    }
}
