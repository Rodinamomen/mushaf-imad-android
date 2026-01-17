package com.mushafimad.library.domain.models

import kotlinx.serialization.Serializable

/**
 * Complete backup of user data
 * Public API - exposed to library consumers
 */
@Serializable
data class UserDataBackup(
    val version: Int = 1,
    val timestamp: Long = System.currentTimeMillis(),
    val bookmarks: List<BookmarkData> = emptyList(),
    val lastReadPositions: List<LastReadPositionData> = emptyList(),
    val searchHistory: List<SearchHistoryData> = emptyList(),
    val preferences: PreferencesData? = null
)

@Serializable
data class BookmarkData(
    val chapterNumber: Int,
    val verseNumber: Int,
    val pageNumber: Int,
    val createdAt: Long,
    val note: String = "",
    val tags: List<String> = emptyList()
)

@Serializable
data class LastReadPositionData(
    val mushafType: String,
    val chapterNumber: Int,
    val verseNumber: Int,
    val pageNumber: Int,
    val lastReadAt: Long,
    val scrollPosition: Float = 0f
)

@Serializable
data class SearchHistoryData(
    val query: String,
    val timestamp: Long,
    val resultCount: Int,
    val searchType: String
)

@Serializable
data class PreferencesData(
    val mushafType: String,
    val currentPage: Int,
    val fontSizeMultiplier: Float,
    val selectedReciterId: Int,
    val playbackSpeed: Float,
    val repeatMode: Boolean,
    val themeMode: String,
    val colorScheme: String,
    val useAmoled: Boolean
)
