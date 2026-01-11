package com.mushafimad.library.data.local.entities

import io.realm.kotlin.types.RealmObject

/**
 * Realm entity for verse highlight coordinates
 * Represents highlighting boundaries for a single line of a verse
 */
@io.realm.kotlin.types.annotations.PersistedName("VerseHighlight")
class VerseHighlightEntity : RealmObject {
    var line: Int = 0
    var left: Float = 0f
    var right: Float = 0f
}
