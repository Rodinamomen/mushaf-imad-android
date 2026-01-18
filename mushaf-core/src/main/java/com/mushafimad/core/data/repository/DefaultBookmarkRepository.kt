package com.mushafimad.core.data.repository

import com.mushafimad.core.data.local.dao.BookmarkDao
import com.mushafimad.core.data.local.entities.BookmarkEntity
import com.mushafimad.core.domain.models.Bookmark
import com.mushafimad.core.domain.repository.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

/**
 * Default implementation of BookmarkRepository using BookmarkDao
 * Internal implementation - not exposed in public API
 * Testable: DAO can be swapped with fake implementation
 */
internal class DefaultBookmarkRepository(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override fun getAllBookmarksFlow(): Flow<List<Bookmark>> {
        return bookmarkDao.getAllFlow()
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getAllBookmarks(): List<Bookmark> = withContext(Dispatchers.IO) {
        bookmarkDao.getAll().map { it.toDomain() }
    }

    override suspend fun getBookmarkById(id: String): Bookmark? = withContext(Dispatchers.IO) {
        val objectId = try {
            ObjectId(id)
        } catch (e: Exception) {
            return@withContext null
        }

        bookmarkDao.getById(objectId)?.toDomain()
    }

    override suspend fun getBookmarksForChapter(chapterNumber: Int): List<Bookmark> = withContext(Dispatchers.IO) {
        bookmarkDao.getForChapter(chapterNumber).map { it.toDomain() }
    }

    override suspend fun getBookmarkForVerse(chapterNumber: Int, verseNumber: Int): Bookmark? = withContext(Dispatchers.IO) {
        bookmarkDao.getForVerse(chapterNumber, verseNumber)?.toDomain()
    }

    override suspend fun addBookmark(
        chapterNumber: Int,
        verseNumber: Int,
        pageNumber: Int,
        note: String,
        tags: List<String>
    ): Bookmark = withContext(Dispatchers.IO) {
        val bookmarkId = bookmarkDao.upsert(
            chapterNumber = chapterNumber,
            verseNumber = verseNumber,
            pageNumber = pageNumber,
            note = note,
            tags = tags.joinToString(","),
            createdAt = System.currentTimeMillis()
        )

        getBookmarkById(bookmarkId.toHexString())!!
    }

    override suspend fun updateBookmarkNote(id: String, note: String) = withContext(Dispatchers.IO) {
        val objectId = ObjectId(id)
        bookmarkDao.updateNote(objectId, note)
    }

    override suspend fun updateBookmarkTags(id: String, tags: List<String>) = withContext(Dispatchers.IO) {
        val objectId = ObjectId(id)
        bookmarkDao.updateTags(objectId, tags.joinToString(","))
    }

    override suspend fun deleteBookmark(id: String): Unit = withContext(Dispatchers.IO) {
        val objectId = ObjectId(id)
        bookmarkDao.delete(objectId)
    }

    override suspend fun deleteBookmarkForVerse(chapterNumber: Int, verseNumber: Int): Unit = withContext(Dispatchers.IO) {
        bookmarkDao.deleteForVerse(chapterNumber, verseNumber)
    }

    override suspend fun deleteAllBookmarks() = withContext(Dispatchers.IO) {
        bookmarkDao.deleteAll()
    }

    override suspend fun isVerseBookmarked(chapterNumber: Int, verseNumber: Int): Boolean = withContext(Dispatchers.IO) {
        bookmarkDao.isVerseBookmarked(chapterNumber, verseNumber)
    }

    override suspend fun searchBookmarks(query: String): List<Bookmark> = withContext(Dispatchers.IO) {
        bookmarkDao.search(query).map { it.toDomain() }
    }

    private fun BookmarkEntity.toDomain(): Bookmark {
        return Bookmark(
            id = id.toHexString(),
            chapterNumber = chapterNumber,
            verseNumber = verseNumber,
            pageNumber = pageNumber,
            createdAt = createdAt,
            note = note,
            tags = if (tags.isBlank()) emptyList() else tags.split(",").map { it.trim() }
        )
    }
}
