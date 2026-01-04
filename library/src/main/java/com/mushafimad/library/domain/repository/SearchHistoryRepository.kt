package com.mushafimad.library.domain.repository

import com.mushafimad.library.domain.models.SearchHistoryEntry
import com.mushafimad.library.domain.models.SearchSuggestion
import com.mushafimad.library.domain.models.SearchType
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing search history and suggestions
 * Public API - exposed to library consumers
 */
interface SearchHistoryRepository {

    /**
     * Observe recent search history
     * @param limit Maximum number of entries to return
     */
    fun getRecentSearchesFlow(limit: Int = 20): Flow<List<SearchHistoryEntry>>

    /**
     * Get recent search history
     * @param limit Maximum number of entries to return
     */
    suspend fun getRecentSearches(limit: Int = 20): List<SearchHistoryEntry>

    /**
     * Record a search query
     * @param query The search query
     * @param resultCount Number of results found
     * @param searchType Type of search performed
     */
    suspend fun recordSearch(
        query: String,
        resultCount: Int,
        searchType: SearchType = SearchType.GENERAL
    )

    /**
     * Get search suggestions based on history
     * @param prefix Optional prefix to filter suggestions
     * @param limit Maximum number of suggestions
     */
    suspend fun getSearchSuggestions(
        prefix: String? = null,
        limit: Int = 10
    ): List<SearchSuggestion>

    /**
     * Get most popular searches
     * @param limit Maximum number of entries
     */
    suspend fun getPopularSearches(limit: Int = 10): List<SearchSuggestion>

    /**
     * Delete a search history entry
     * @param id The search history entry ID
     */
    suspend fun deleteSearch(id: String)

    /**
     * Delete all search history older than a timestamp
     * @param timestamp Timestamp in milliseconds
     */
    suspend fun deleteSearchesOlderThan(timestamp: Long)

    /**
     * Clear all search history
     */
    suspend fun clearSearchHistory()

    /**
     * Get search history for a specific type
     * @param searchType The search type
     * @param limit Maximum number of entries
     */
    suspend fun getSearchesByType(searchType: SearchType, limit: Int = 20): List<SearchHistoryEntry>
}
