package com.mushafimad.library.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

/**
 * Realm entity for page header information
 * Contains Part and Quarter information for the page
 */
class PageHeaderEntity : RealmObject {
    var part: PartEntity? = null
    var quarter: QuarterEntity? = null
    var chapters: RealmList<ChapterEntity> = realmListOf()
}
