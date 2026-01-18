package com.mushafimad.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.mushafimad.core.domain.models.ColorScheme
import com.mushafimad.core.domain.models.MushafType
import com.mushafimad.core.domain.models.ThemeConfig
import com.mushafimad.core.domain.models.ThemeMode
import com.mushafimad.core.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Extension property for DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "mushaf_preferences")

/**
 * Consolidated implementation of PreferencesRepository using DataStore
 * Stores all user preferences in a single DataStore file
 * Internal API - not exposed to library consumers
 */
internal class DefaultPreferencesRepository (
    private val context: Context
) : PreferencesRepository {

    private val dataStore = context.dataStore

    companion object {
        // Mushaf reading preferences
        private val MUSHAF_TYPE_KEY = stringPreferencesKey("mushaf_type")
        private val CURRENT_PAGE_KEY = intPreferencesKey("current_page")
        private val LAST_READ_CHAPTER_KEY = intPreferencesKey("last_read_chapter")
        private val LAST_READ_CHAPTER_NUMBER_KEY = intPreferencesKey("last_read_chapter_number")
        private val LAST_READ_VERSE_NUMBER_KEY = intPreferencesKey("last_read_verse_number")
        private val FONT_SIZE_MULTIPLIER_KEY = floatPreferencesKey("font_size_multiplier")
        private val SHOW_TRANSLATION_KEY = booleanPreferencesKey("show_translation")

        // Audio preferences
        private val SELECTED_RECITER_ID_KEY = intPreferencesKey("selected_reciter_id")
        private val PLAYBACK_SPEED_KEY = floatPreferencesKey("playback_speed")
        private val REPEAT_MODE_KEY = booleanPreferencesKey("repeat_mode")

        // Theme preferences
        private val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        private val COLOR_SCHEME_KEY = stringPreferencesKey("color_scheme")
        private val AMOLED_MODE_KEY = booleanPreferencesKey("amoled_mode")

        // Defaults
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_FONT_SIZE_MULTIPLIER = 1.0f
        private const val DEFAULT_RECITER_ID = 1 // Ibrahim Al-Akdar
        private const val DEFAULT_PLAYBACK_SPEED = 1.0f
        private const val DEFAULT_REPEAT_MODE = false
    }

    override fun getMushafTypeFlow(): Flow<MushafType> = dataStore.data.map { preferences ->
        val typeString = preferences[MUSHAF_TYPE_KEY] ?: MushafType.HAFS_1441.name
        try {
            MushafType.valueOf(typeString)
        } catch (e: IllegalArgumentException) {
            MushafType.HAFS_1441
        }
    }

    override suspend fun setMushafType(mushafType: MushafType) {
        dataStore.edit { preferences ->
            preferences[MUSHAF_TYPE_KEY] = mushafType.name
        }
    }

    override fun getCurrentPageFlow(): Flow<Int> = dataStore.data.map { preferences ->
        preferences[CURRENT_PAGE_KEY] ?: DEFAULT_PAGE
    }

    override suspend fun setCurrentPage(pageNumber: Int) {
        dataStore.edit { preferences ->
            preferences[CURRENT_PAGE_KEY] = pageNumber
        }
    }

    override fun getLastReadChapterFlow(): Flow<Int?> = dataStore.data.map { preferences ->
        preferences[LAST_READ_CHAPTER_KEY]
    }

    override suspend fun setLastReadChapter(chapterNumber: Int) {
        dataStore.edit { preferences ->
            preferences[LAST_READ_CHAPTER_KEY] = chapterNumber
        }
    }

    override fun getLastReadVerseFlow(): Flow<Pair<Int, Int>?> = dataStore.data.map { preferences ->
        val chapterNumber = preferences[LAST_READ_CHAPTER_NUMBER_KEY]
        val verseNumber = preferences[LAST_READ_VERSE_NUMBER_KEY]

        if (chapterNumber != null && verseNumber != null) {
            Pair(chapterNumber, verseNumber)
        } else {
            null
        }
    }

    override suspend fun setLastReadVerse(chapterNumber: Int, verseNumber: Int) {
        dataStore.edit { preferences ->
            preferences[LAST_READ_CHAPTER_NUMBER_KEY] = chapterNumber
            preferences[LAST_READ_VERSE_NUMBER_KEY] = verseNumber
        }
    }

    override fun getFontSizeMultiplierFlow(): Flow<Float> = dataStore.data.map { preferences ->
        preferences[FONT_SIZE_MULTIPLIER_KEY] ?: DEFAULT_FONT_SIZE_MULTIPLIER
    }

    override suspend fun setFontSizeMultiplier(multiplier: Float) {
        dataStore.edit { preferences ->
            // Clamp between 0.5 and 2.0
            val clamped = multiplier.coerceIn(0.5f, 2.0f)
            preferences[FONT_SIZE_MULTIPLIER_KEY] = clamped
        }
    }

    override fun getShowTranslationFlow(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[SHOW_TRANSLATION_KEY] ?: false
    }

    override suspend fun setShowTranslation(show: Boolean) {
        dataStore.edit { preferences ->
            preferences[SHOW_TRANSLATION_KEY] = show
        }
    }

    // ========== Audio Preferences Implementation ==========

    override fun getSelectedReciterIdFlow(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[SELECTED_RECITER_ID_KEY] ?: DEFAULT_RECITER_ID
        }
    }

    override suspend fun getSelectedReciterId(): Int {
        return getSelectedReciterIdFlow().first()
    }

    override suspend fun setSelectedReciterId(reciterId: Int) {
        dataStore.edit { preferences ->
            preferences[SELECTED_RECITER_ID_KEY] = reciterId
        }
    }

    override fun getPlaybackSpeedFlow(): Flow<Float> {
        return dataStore.data.map { preferences ->
            preferences[PLAYBACK_SPEED_KEY] ?: DEFAULT_PLAYBACK_SPEED
        }
    }

    override suspend fun getPlaybackSpeed(): Float {
        return getPlaybackSpeedFlow().first()
    }

    override suspend fun setPlaybackSpeed(speed: Float) {
        val clampedSpeed = speed.coerceIn(0.5f, 3.0f)
        dataStore.edit { preferences ->
            preferences[PLAYBACK_SPEED_KEY] = clampedSpeed
        }
    }

    override fun getRepeatModeFlow(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[REPEAT_MODE_KEY] ?: DEFAULT_REPEAT_MODE
        }
    }

    override suspend fun getRepeatMode(): Boolean {
        return getRepeatModeFlow().first()
    }

    override suspend fun setRepeatMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[REPEAT_MODE_KEY] = enabled
        }
    }

    // ========== Theme Preferences Implementation ==========

    override fun getThemeConfigFlow(): Flow<ThemeConfig> {
        return dataStore.data.map { preferences ->
            ThemeConfig(
                mode = preferences[THEME_MODE_KEY]?.let {
                    ThemeMode.valueOf(it)
                } ?: ThemeMode.SYSTEM,
                colorScheme = preferences[COLOR_SCHEME_KEY]?.let {
                    ColorScheme.valueOf(it)
                } ?: ColorScheme.DEFAULT,
                useAmoled = preferences[AMOLED_MODE_KEY] ?: false
            )
        }
    }

    override suspend fun getThemeConfig(): ThemeConfig {
        return getThemeConfigFlow().first()
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode.name
        }
    }

    override suspend fun setColorScheme(scheme: ColorScheme) {
        dataStore.edit { preferences ->
            preferences[COLOR_SCHEME_KEY] = scheme.name
        }
    }

    override suspend fun setAmoledMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[AMOLED_MODE_KEY] = enabled
        }
    }

    override suspend fun updateThemeConfig(config: ThemeConfig) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = config.mode.name
            preferences[COLOR_SCHEME_KEY] = config.colorScheme.name
            preferences[AMOLED_MODE_KEY] = config.useAmoled
        }
    }

    // ========== General ==========

    override suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
