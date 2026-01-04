package com.mushafimad.library.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Realm entity for Quran Section
 * Groups verses into logical sections
 */
class QuranSectionEntity : RealmObject {
    @PrimaryKey
    var identifier: Int = 0

    var verses: RealmList<VerseEntity> = realmListOf()
}
