package com.mushafimad.library.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Realm entity for Quran Verse (Ayah)
 * Schema version: 24 (compatible with iOS)
 */
class VerseEntity : RealmObject {
    @PrimaryKey
    var verseID: Int = 0

    @Index
    var humanReadableID: String = ""  // e.g. "2_255"

    @Index
    var number: Int = 0

    var text: String = ""
    var textWithoutTashkil: String = ""
    var uthmanicHafsText: String = ""
    var hafsSmartText: String = ""

    @Index
    var searchableText: String = ""

    // Relationships
    var chapter: ChapterEntity? = null
    var part: PartEntity? = null
    var quarter: QuarterEntity? = null
    var section: QuranSectionEntity? = null
    var page1441: PageEntity? = null
    var page1405: PageEntity? = null

    // Markers and highlights
    var marker1441: VerseMarkerEntity? = null
    var marker1405: VerseMarkerEntity? = null
    var highlights1441: RealmList<VerseHighlightEntity> = realmListOf()
    var highlights1405: RealmList<VerseHighlightEntity> = realmListOf()
}
