package com.mushafimad.core.data.local.entities

import io.realm.kotlin.types.RealmObject

/**
 * Realm entity for chapter header display information
 * Contains the chapter reference and its display position on the page
 */
@io.realm.kotlin.types.annotations.PersistedName("ChapterHeader")
class ChapterHeaderEntity : RealmObject {
    var chapter: ChapterEntity? = null
    var page: PageEntity? = null
    var line: Int = 0
    var centerX: Float = 0f
    var centerY: Float = 0f
}
