package com.mushafimad.core.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Realm entity for Quran Chapter (Surah)
 * Schema version: 24 (compatible with iOS)
 * Maps to "Chapter" table in bundled iOS realm file
 */
@io.realm.kotlin.types.annotations.PersistedName("Chapter")
class ChapterEntity : RealmObject {
    @PrimaryKey
    var identifier: Int = 0

    @Index
    var number: Int = 0

    var isMeccan: Boolean = false
    var title: String = ""
    var arabicTitle: String = ""
    var englishTitle: String = ""
    var titleCodePoint: String = ""

    @Index
    var searchableText: String = ""
    var searchableKeywords: String = ""

    var verses: RealmList<VerseEntity> = realmListOf()
    var header1441: ChapterHeaderEntity? = null
    var header1405: ChapterHeaderEntity? = null
}
