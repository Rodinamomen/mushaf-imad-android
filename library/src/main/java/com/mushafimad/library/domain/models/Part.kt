package com.mushafimad.library.domain.models

/**
 * Domain model for Quran Part (Juz)
 * Represents one of the 30 parts of the Quran
 * Public API - exposed to library consumers
 */
data class Part(
    val identifier: Int,
    val number: Int,
    val arabicTitle: String,
    val englishTitle: String
) {
    /**
     * Get the display title based on current locale
     */
    fun getDisplayTitle(languageCode: String = "en"): String {
        return if (languageCode == "ar") arabicTitle else englishTitle
    }
}
