package com.mushafimad.library.domain.models

/**
 * Search query types
 * Public API - exposed to library consumers
 */
enum class SearchType {
    VERSE,      // Search in verse text
    CHAPTER,    // Search in chapter names
    GENERAL     // General search across all fields
}

/**
 * Search history entry
 * Public API - exposed to library consumers
 */
data class SearchHistoryEntry(
    val id: String,
    val query: String,
    val timestamp: Long,
    val resultCount: Int,
    val searchType: SearchType
) {
    /**
     * Check if this search is recent (within last 24 hours)
     */
    fun isRecent(): Boolean {
        val oneDayAgo = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        return timestamp > oneDayAgo
    }
}

/**
 * Search suggestion based on history
 * Public API - exposed to library consumers
 */
data class SearchSuggestion(
    val query: String,
    val frequency: Int,      // How many times this query was searched
    val lastSearched: Long   // Timestamp of last search
)
