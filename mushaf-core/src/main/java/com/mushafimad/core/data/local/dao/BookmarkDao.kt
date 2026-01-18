package com.mushafimad.core.data.local.dao

import com.mushafimad.core.data.local.entities.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

/**
 * Data Access Object for Bookmark entities
 * Abstracts Realm operations for testing and flexibility
 *
 * @internal This interface is not part of the public API
 */
internal interface BookmarkDao {

    /**
     * Get all bookmarks as a Flow (sorted by creation date descending)
     */
    fun getAllFlow(): Flow<List<BookmarkEntity>>

    /**
     * Get all bookmarks (sorted by creation date descending)
     */
    suspend fun getAll(): List<BookmarkEntity>

    /**
     * Get bookmark by ID
     */
    suspend fun getById(id: ObjectId): BookmarkEntity?

    /**
     * Get bookmarks for a specific chapter (sorted by verse number)
     */
    suspend fun getForChapter(chapterNumber: Int): List<BookmarkEntity>

    /**
     * Get bookmark for a specific verse
     */
    suspend fun getForVerse(chapterNumber: Int, verseNumber: Int): BookmarkEntity?

    /**
     * Insert or update a bookmark
     * @return The ID of the inserted/updated bookmark
     */
    suspend fun upsert(
        chapterNumber: Int,
        verseNumber: Int,
        pageNumber: Int,
        note: String,
        tags: String,
        createdAt: Long = System.currentTimeMillis()
    ): ObjectId

    /**
     * Update bookmark note
     */
    suspend fun updateNote(id: ObjectId, note: String)

    /**
     * Update bookmark tags
     */
    suspend fun updateTags(id: ObjectId, tags: String)

    /**
     * Delete bookmark by ID
     */
    suspend fun delete(id: ObjectId)

    /**
     * Delete bookmark for a specific verse
     */
    suspend fun deleteForVerse(chapterNumber: Int, verseNumber: Int)

    /**
     * Delete all bookmarks
     */
    suspend fun deleteAll()

    /**
     * Check if a verse is bookmarked
     */
    suspend fun isVerseBookmarked(chapterNumber: Int, verseNumber: Int): Boolean

    /**
     * Search bookmarks by query (case-insensitive search in note and tags)
     */
    suspend fun search(query: String): List<BookmarkEntity>
}
