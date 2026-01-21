package com.mushafimad.core.domain.repository

import com.mushafimad.core.domain.models.ColorScheme
import com.mushafimad.core.domain.models.MushafType
import com.mushafimad.core.domain.models.ThemeConfig
import com.mushafimad.core.domain.models.ThemeMode
import kotlinx.coroutines.flow.Flow

/**
 * Repository for all user preferences and settings
 * Public API - exposed to library consumers
 *
 * Consolidated repository for:
 * - Mushaf reading preferences (page, chapter, verse, font size, translation)
 * - Audio preferences (reciter, playback speed, repeat mode)
 * - Theme preferences (theme mode, color scheme, AMOLED mode)
 */
interface PreferencesRepository {

    // ========== Mushaf Reading Preferences ==========

    /**
     * Get the selected Mushaf type as a Flow
     */
    fun getMushafTypeFlow(): Flow<MushafType>

    /**
     * Set the selected Mushaf type
     */
    suspend fun setMushafType(mushafType: MushafType)

    /**
     * Get the current page number as a Flow
     */
    fun getCurrentPageFlow(): Flow<Int>

    /**
     * Set the current page number
     */
    suspend fun setCurrentPage(pageNumber: Int)

    /**
     * Get the last read chapter number as a Flow
     */
    fun getLastReadChapterFlow(): Flow<Int?>

    /**
     * Set the last read chapter number
     */
    suspend fun setLastReadChapter(chapterNumber: Int)

    /**
     * Get the last read verse as a Flow
     */
    fun getLastReadVerseFlow(): Flow<Pair<Int, Int>?>  // (chapterNumber, verseNumber)

    /**
     * Set the last read verse
     */
    suspend fun setLastReadVerse(chapterNumber: Int, verseNumber: Int)

    /**
     * Get the font size multiplier as a Flow
     */
    fun getFontSizeMultiplierFlow(): Flow<Float>

    /**
     * Set the font size multiplier (0.5 to 2.0)
     */
    suspend fun setFontSizeMultiplier(multiplier: Float)

    /**
     * Get whether to show translation
     */
    fun getShowTranslationFlow(): Flow<Boolean>

    /**
     * Set whether to show translation
     */
    suspend fun setShowTranslation(show: Boolean)

    // ========== Audio Preferences ==========

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
     * @param speed Playback speed (0.5 - 3.0)
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

    /**
     * Observe last played audio chapter
     */
    fun getLastAudioChapterFlow(): Flow<Int?>

    /**
     * Get last played audio chapter
     */
    suspend fun getLastAudioChapter(): Int?

    /**
     * Set last played audio chapter
     * @param chapterNumber Chapter number (1-114), or null to clear
     */
    suspend fun setLastAudioChapter(chapterNumber: Int?)

    /**
     * Observe last played audio verse
     */
    fun getLastAudioVerseFlow(): Flow<Int?>

    /**
     * Get last played audio verse
     */
    suspend fun getLastAudioVerse(): Int?

    /**
     * Set last played audio verse
     * @param verseNumber Verse number, or null to clear
     */
    suspend fun setLastAudioVerse(verseNumber: Int?)

    /**
     * Observe last audio playback position in milliseconds
     */
    fun getLastAudioPositionMsFlow(): Flow<Long>

    /**
     * Get last audio playback position in milliseconds
     */
    suspend fun getLastAudioPositionMs(): Long

    /**
     * Set last audio playback position
     * @param positionMs Position in milliseconds
     */
    suspend fun setLastAudioPositionMs(positionMs: Long)

    // ========== Theme Preferences ==========

    /**
     * Observe theme configuration
     */
    fun getThemeConfigFlow(): Flow<ThemeConfig>

    /**
     * Get current theme configuration
     */
    suspend fun getThemeConfig(): ThemeConfig

    /**
     * Set theme mode
     * @param mode The theme mode (LIGHT, DARK, SYSTEM)
     */
    suspend fun setThemeMode(mode: ThemeMode)

    /**
     * Set color scheme
     * @param scheme The color scheme
     */
    suspend fun setColorScheme(scheme: ColorScheme)

    /**
     * Set AMOLED mode (pure black for dark theme)
     * @param enabled Whether to use AMOLED black
     */
    suspend fun setAmoledMode(enabled: Boolean)

    /**
     * Update complete theme configuration
     * @param config The new theme configuration
     */
    suspend fun updateThemeConfig(config: ThemeConfig)

    // ========== General ==========

    /**
     * Clear all preferences
     */
    suspend fun clearAll()
}
