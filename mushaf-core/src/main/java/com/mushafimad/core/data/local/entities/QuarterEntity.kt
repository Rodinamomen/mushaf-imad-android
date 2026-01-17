package com.mushafimad.library.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Realm entity for Quran Quarter (Hizb fraction)
 * Represents one-quarter of a Hizb
 * Schema version: 24 (compatible with iOS)
 */
@io.realm.kotlin.types.annotations.PersistedName("Quarter")
class QuarterEntity : RealmObject {
    @PrimaryKey
    var identifier: Int = 0

    @Index
    var hizbNumber: Int = 0

    var hizbFraction: Int = 0  // 0=start, 1=quarter, 2=half, 3=three-quarters

    var arabicTitle: String = ""
    var englishTitle: String = ""

    var part: PartEntity? = null
    var verses: RealmList<VerseEntity> = realmListOf()
}
