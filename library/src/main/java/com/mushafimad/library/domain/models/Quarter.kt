package com.mushafimad.library.domain.models

/**
 * Domain model for Quran Quarter (Hizb fraction)
 * Represents one-quarter of a Hizb
 * Public API - exposed to library consumers
 */
data class Quarter(
    val identifier: Int,
    val hizbNumber: Int,
    val hizbFraction: Int,  // 0=start, 1=quarter, 2=half, 3=three-quarters
    val arabicTitle: String,
    val englishTitle: String,
    val partNumber: Int = 0
) {
    /**
     * Get the display title based on current locale
     */
    fun getDisplayTitle(languageCode: String = "en"): String {
        return if (languageCode == "ar") arabicTitle else englishTitle
    }

    /**
     * Get the Hizb quarter progress as a fraction
     */
    fun getProgressFraction(): HizbQuarterProgress? {
        return when (hizbFraction) {
            1 -> HizbQuarterProgress.QUARTER
            2 -> HizbQuarterProgress.HALF
            3 -> HizbQuarterProgress.THREE_QUARTERS
            else -> null
        }
    }
}

/**
 * Enum representing Hizb quarter progress
 */
enum class HizbQuarterProgress {
    QUARTER,
    HALF,
    THREE_QUARTERS
}
