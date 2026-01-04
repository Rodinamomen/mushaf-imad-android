package com.mushafimad.library.domain.repository

import com.mushafimad.library.domain.models.UserDataBackup

/**
 * Repository for exporting and importing user data
 * Public API - exposed to library consumers
 */
interface DataExportRepository {

    /**
     * Export all user data to a backup object
     * @param includeHistory Whether to include reading history (can be large)
     * @return UserDataBackup containing all user data
     */
    suspend fun exportUserData(includeHistory: Boolean = true): UserDataBackup

    /**
     * Export user data to JSON string
     * @param includeHistory Whether to include reading history
     * @return JSON string containing all user data
     */
    suspend fun exportToJson(includeHistory: Boolean = true): String

    /**
     * Import user data from a backup object
     * @param backup The backup to import
     * @param mergeWithExisting If true, merge with existing data. If false, replace all data.
     * @return Number of items imported
     */
    suspend fun importUserData(backup: UserDataBackup, mergeWithExisting: Boolean = true): ImportResult

    /**
     * Import user data from JSON string
     * @param json JSON string containing user data
     * @param mergeWithExisting If true, merge with existing data. If false, replace all data.
     * @return Number of items imported
     */
    suspend fun importFromJson(json: String, mergeWithExisting: Boolean = true): ImportResult

    /**
     * Clear all user data (bookmarks, history, etc.)
     * Use with caution!
     */
    suspend fun clearAllUserData()
}

/**
 * Result of data import operation
 * Public API - exposed to library consumers
 */
data class ImportResult(
    val bookmarksImported: Int,
    val lastReadPositionsImported: Int,
    val searchHistoryImported: Int,
    val preferencesImported: Boolean,
    val errors: List<String> = emptyList()
) {
    val totalItemsImported: Int
        get() = bookmarksImported + lastReadPositionsImported + searchHistoryImported

    val hasErrors: Boolean
        get() = errors.isNotEmpty()
}
