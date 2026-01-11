package com.mushafimad.library.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Realm entity for Quran Part (Juz)
 * Represents one of the 30 parts of the Quran
 * Schema version: 24 (compatible with iOS)
 */
@io.realm.kotlin.types.annotations.PersistedName("Part")
class PartEntity : RealmObject {
    @PrimaryKey
    var identifier: Int = 0

    @Index
    var number: Int = 0

    var arabicTitle: String = ""
    var englishTitle: String = ""

    var chapters: RealmList<ChapterEntity> = realmListOf()
    var quarters: RealmList<QuarterEntity> = realmListOf()
    var verses: RealmList<VerseEntity> = realmListOf()
}
