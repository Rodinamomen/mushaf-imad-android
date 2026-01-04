package com.mushafimad.library.domain.models

/**
 * Chapters grouped by Juz (Part)
 * Public API - exposed to library consumers
 */
data class ChaptersByPart(
    val id: Int,
    val partNumber: Int,
    val arabicTitle: String,
    val englishTitle: String,
    val chapters: List<Chapter>,
    val firstPage: Int?,
    val firstVerse: Verse?
)

/**
 * Chapters grouped by Quarter (Rub al-Hizb)
 * Public API - exposed to library consumers
 */
data class ChaptersByQuarter(
    val id: Int,
    val quarterNumber: Int,
    val hizbNumber: Int,
    val hizbFraction: Int,
    val arabicTitle: String,
    val englishTitle: String,
    val chapters: List<Chapter>,
    val firstPage: Int?,
    val firstVerse: Verse?
)

/**
 * Chapters grouped by Hizb
 * Public API - exposed to library consumers
 */
data class ChaptersByHizb(
    val id: Int,
    val hizbNumber: Int,
    val quarters: List<ChaptersByQuarter>
) {
    val hizbTitle: String
        get() = "الحزب $hizbNumber"
}

/**
 * Chapters grouped by type (Meccan/Medinan)
 * Public API - exposed to library consumers
 */
data class ChaptersByType(
    val id: String,
    val type: String,
    val arabicType: String,
    val chapters: List<Chapter>,
    val firstPage: Int?,
    val firstVerse: Verse?
) {
    val isMeccan: Boolean
        get() = id == "meccan"
}
