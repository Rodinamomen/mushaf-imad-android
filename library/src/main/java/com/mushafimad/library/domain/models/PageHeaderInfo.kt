package com.mushafimad.library.domain.models

/**
 * Lightweight structure describing the contextual header for a Mushaf page
 * Public API - exposed to library consumers
 */
data class PageHeaderInfo(
    val partNumber: Int?,
    val partArabicTitle: String?,
    val partEnglishTitle: String?,
    val hizbNumber: Int?,
    val hizbFraction: Int?,
    val quarterArabicTitle: String?,
    val quarterEnglishTitle: String?,
    val chapters: List<ChapterInfo>
) {
    /**
     * Get the Hizb quarter progress based on fraction
     */
    val hizbQuarterProgress: HizbQuarterProgress?
        get() = when (hizbFraction) {
            1 -> HizbQuarterProgress.QUARTER
            2 -> HizbQuarterProgress.HALF
            3 -> HizbQuarterProgress.THREE_QUARTERS
            else -> null
        }

    /**
     * Get the Hizb display string in Arabic
     */
    val hizbDisplay: String?
        get() {
            val hizbNum = hizbNumber ?: return null
            val fraction = hizbFraction ?: 0

            return when {
                fraction == 1 -> "¼ الحزب $hizbNum"
                fraction == 2 -> "½ الحزب $hizbNum"
                fraction == 3 -> "¾ الحزب $hizbNum"
                else -> "الحزب $hizbNum"
            }
        }

    /**
     * Get the Juz display string in Arabic
     */
    val juzDisplay: String?
        get() {
            val partNum = partNumber ?: return null
            return "الجزء $partNum"
        }
}

/**
 * Summary of a chapter suitable for displaying in headers and lists
 * Public API - exposed to library consumers
 */
data class ChapterInfo(
    val number: Int,
    val arabicTitle: String,
    val englishTitle: String
)
