package com.adewan.mystuff.core.models.games

enum class QueryField(val fieldName: String) {
    SLUG("slug"),
    NAME("name"),
    ARTWORKS_IMAGE_ID("artworks.image_id"),
    FIRST_RELEASE_DATE("first_release_date"),
    COVER_IMAGE_ID("cover.image_id"),
    CATEGORY("category"),
    TOTAL_RATING("total_rating"),
    TOTAL_RATING_COUNT("total_rating_count"),
    GENRE("genres"),
    GENRE_NAME("genres.name"),
    GENRE_SLUG("genres.slug"),
    STORYLINE("storyline"),
    THEMES("themes"),
    THEMES_NAME("themes.name"),
    RATING("rating"),
    HYPES("hypes"),
    PLATFORM("platforms"),
    VIDEOS_NAME("videos.name"),
    VIDEOS_ID("videos.video_id"),
    SUMMARY("summary"),
    SCREENSHOTS_ID("screenshots.image_id"),
    PLATFORM_LOGO_IMAGE_ID("platforms.platform_logo.image_id"),
    SIMILAR_GAMES_SLUG("similar_games.slug"),
    SIMILAR_GAMES_COVER_MAGE_ID("similar_games.cover.image_id")
}
