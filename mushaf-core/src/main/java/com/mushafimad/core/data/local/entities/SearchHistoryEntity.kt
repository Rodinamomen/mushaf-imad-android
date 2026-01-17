package com.mushafimad.library.data.local.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/**
 * Realm entity for search history
 * Tracks user's search queries for suggestions
 * Internal data model - not exposed in public API
 */
internal class SearchHistoryEntity : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var query: String = ""
    var timestamp: Long = 0           // Unix timestamp in milliseconds
    var resultCount: Int = 0          // Number of results found
    var searchType: String = ""       // "verse", "chapter", "general"
}
