package com.adewan.mystuff.core.models.games

enum class QueryField(val fieldName: String) {
    SLUG("slug"),
    NAME("name"),
    FIRST_RELEASE_DATE("first_release_date"),
    COVER_IMAGE_ID("cover.image_id"),
    RATING("rating"),
    HYPES("hypes")
}
