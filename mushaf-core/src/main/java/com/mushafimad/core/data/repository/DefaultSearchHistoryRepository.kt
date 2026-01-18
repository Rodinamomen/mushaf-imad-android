package com.mushafimad.core.data.repository

import com.mushafimad.core.data.local.dao.SearchHistoryDao
import com.mushafimad.core.data.local.entities.SearchHistoryEntity
import com.mushafimad.core.domain.models.SearchHistoryEntry
import com.mushafimad.core.domain.models.SearchSuggestion
import com.mushafimad.core.domain.models.SearchType
import com.mushafimad.core.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

/**
 * Default implementation of SearchHistoryRepository using SearchHistoryDao
 * Fully testable - DAO can be swapped with fake implementation
 */
internal class DefaultSearchHistoryRepository (
    private val searchHistoryDao: SearchHistoryDao
) : SearchHistoryRepository {

    override fun getRecentSearchesFlow(limit: Int): Flow<List<SearchHistoryEntry>> {
        return searchHistoryDao.getRecentSearchesFlow(limit)
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getRecentSearches(limit: Int): List<SearchHistoryEntry> = withContext(Dispatchers.IO) {
        searchHistoryDao.getRecentSearches(limit).map { it.toDomain() }
    }

    override suspend fun recordSearch(
        query: String,
        resultCount: Int,
        searchType: SearchType
    ): Unit = withContext(Dispatchers.IO) {
        // Don't record empty queries
        if (query.isBlank()) return@withContext

        searchHistoryDao.insertSearch(
            query = query.trim(),
            resultCount = resultCount,
            searchType = searchType.name,
            timestamp = System.currentTimeMillis()
        )

        // Clean up old history (keep last 100 entries)
        cleanupOldHistory(limit = 100)
    }

    override suspend fun getSearchSuggestions(
        prefix: String?,
        limit: Int
    ): List<SearchSuggestion> = withContext(Dispatchers.IO) {
        val searches = if (prefix != null && prefix.isNotBlank()) {
            searchHistoryDao.getByPrefix(prefix)
        } else {
            searchHistoryDao.getAllSearches()
        }

        // Group by query and count frequency
        searches
            .groupBy { it.query.lowercase() }
            .map { (query, entries) ->
                SearchSuggestion(
                    query = entries.first().query,
                    frequency = entries.size,
                    lastSearched = entries.maxOf { it.timestamp }
                )
            }
            .sortedWith(compareByDescending<SearchSuggestion> { it.frequency }
                .thenByDescending { it.lastSearched })
            .take(limit)
    }

    override suspend fun getPopularSearches(limit: Int): List<SearchSuggestion> = withContext(Dispatchers.IO) {
        val searches = searchHistoryDao.getAllSearches()

        searches
            .groupBy { it.query.lowercase() }
            .map { (query, entries) ->
                SearchSuggestion(
                    query = entries.first().query,
                    frequency = entries.size,
                    lastSearched = entries.maxOf { it.timestamp }
                )
            }
            .sortedByDescending { it.frequency }
            .take(limit)
    }

    override suspend fun deleteSearch(id: String): Unit = withContext(Dispatchers.IO) {
        val objectId = try {
            ObjectId(id)
        } catch (e: Exception) {
            return@withContext
        }

        searchHistoryDao.delete(objectId)
    }

    override suspend fun deleteSearchesOlderThan(timestamp: Long): Unit = withContext(Dispatchers.IO) {
        searchHistoryDao.deleteOlderThan(timestamp)
    }

    override suspend fun clearSearchHistory(): Unit = withContext(Dispatchers.IO) {
        searchHistoryDao.deleteAll()
    }

    override suspend fun getSearchesByType(searchType: SearchType, limit: Int): List<SearchHistoryEntry> = withContext(Dispatchers.IO) {
        searchHistoryDao.getByType(searchType.name, limit).map { it.toDomain() }
    }

    /**
     * Clean up old history entries, keeping only the most recent ones
     */
    private suspend fun cleanupOldHistory(limit: Int) = withContext(Dispatchers.IO) {
        searchHistoryDao.removeOldestEntries(limit)
    }

    private fun SearchHistoryEntity.toDomain() = SearchHistoryEntry(
        id = id.toHexString(),
        query = query,
        timestamp = timestamp,
        resultCount = resultCount,
        searchType = SearchType.valueOf(searchType)
    )
}
