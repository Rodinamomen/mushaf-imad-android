package com.mushafimad.core.domain.models

/**
 * Supported Mushaf layouts that alter how verses map to pages
 * Public API - exposed to library consumers
 */
enum class MushafType {
    /** Modern layout (1441 Hijri) */
    HAFS_1441,

    /** Traditional layout (1405 Hijri) */
    HAFS_1405
}
