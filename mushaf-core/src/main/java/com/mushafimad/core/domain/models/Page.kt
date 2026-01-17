package com.mushafimad.library.domain.models

/**
 * Domain model for Quran Page
 * Public API - exposed to library consumers
 */
data class Page(
    val identifier: Int,
    val number: Int,
    val isRight: Boolean
)
