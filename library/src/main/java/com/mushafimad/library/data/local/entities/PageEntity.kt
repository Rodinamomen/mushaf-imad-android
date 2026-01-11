package com.mushafimad.library.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Realm entity for Quran Page
 * Represents a single page in the Mushaf
 * Schema version: 24 (compatible with iOS)
 */
@io.realm.kotlin.types.annotations.PersistedName("Page")
class PageEntity : RealmObject {
    @PrimaryKey
    var identifier: Int = 0

    @Index
    var number: Int = 0

    var isRight: Boolean = false

    // Page headers for different Mushaf types
    var header1441: PageHeaderEntity? = null
    var header1405: PageHeaderEntity? = null

    // Chapter headers on this page
    var chapterHeaders1441: RealmList<ChapterHeaderEntity> = realmListOf()
    var chapterHeaders1405: RealmList<ChapterHeaderEntity> = realmListOf()

    // Verses on this page
    var verses1441: RealmList<VerseEntity> = realmListOf()
    var verses1405: RealmList<VerseEntity> = realmListOf()
}
