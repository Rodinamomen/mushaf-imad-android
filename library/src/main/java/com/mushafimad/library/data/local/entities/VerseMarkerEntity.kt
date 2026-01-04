package com.mushafimad.library.data.local.entities

import io.realm.kotlin.types.RealmObject

/**
 * Realm entity for verse number marker position
 * Contains the Unicode code point for the verse number and its display coordinates
 */
class VerseMarkerEntity : RealmObject {
    var numberCodePoint: String = ""
    var line: Int = 0
    var centerX: Float = 0f
    var centerY: Float = 0f
}
