package com.mushafimad.core.data.local.dao

import com.mushafimad.core.data.local.entities.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

/**
 * Data Access Object for Search History entities
 * Abstracts Realm operations for testing and flexibility
 *
 * @internal This interface is not part of the public API
 */
internal interface SearchHistoryDao {

    /**
     * Get recent searches as a Flow (sorted by timestamp descending)
     */
    fun getRecentSearchesFlow(limit: Int): Flow<List<SearchHistoryEntity>>

    /**
     * Get recent searches (sorted by timestamp descending)
     */
    suspend fun getRecentSearches(limit: Int): List<SearchHistoryEntity>

    /**
     * Get all search history
     */
    suspend fun getAllSearches(): List<SearchHistoryEntity>

    /**
     * Insert or update a search history entry
     * If the same query exists, updates the timestamp and result count
     */
    suspend fun insertSearch(
        query: String,
        resultCount: Int,
        searchType: String,
        timestamp: Long
    ): ObjectId

    /**
     * Get searches by prefix (for autocomplete suggestions)
     */
    suspend fun getByPrefix(prefix: String): List<SearchHistoryEntity>

    /**
     * Get searches by type
     */
    suspend fun getByType(searchType: String, limit: Int): List<SearchHistoryEntity>

    /**
     * Delete search by ID
     */
    suspend fun delete(id: ObjectId)

    /**
     * Delete searches older than timestamp
     */
    suspend fun deleteOlderThan(timestamp: Long)

    /**
     * Delete all search history
     */
    suspend fun deleteAll()

    /**
     * Get count of search history entries
     */
    suspend fun getCount(): Long

    /**
     * Remove oldest entries to maintain max size
     */
    suspend fun removeOldestEntries(keepCount: Int)
}
