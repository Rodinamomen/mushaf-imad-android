package com.mushafimad.core.domain.models

/**
 * Domain model for Quran Chapter (Surah)
 * Public API - exposed to library consumers
 */
data class Chapter(
    val identifier: Int,
    val number: Int,
    val isMeccan: Boolean,
    val title: String,
    val arabicTitle: String,
    val englishTitle: String,
    val titleCodePoint: String,
    val searchableText: String,
    val searchableKeywords: String,
    val versesCount: Int = 0
) {
    /**
     * Get the display title based on current locale
     */
    fun getDisplayTitle(languageCode: String = "en"): String {
        return if (languageCode == "ar") arabicTitle else englishTitle
    }
}
