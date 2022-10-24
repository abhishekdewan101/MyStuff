package com.adewan.mystuff.core.model

import kotlinx.serialization.SerialName

enum class TmdbListType(val endpoint: String) {
    POPULAR_MOVIES("/movie/popular"),
    TOP_RATED_MOVIES("/movie/top_rated"),
    UPCOMING_MOVIES("/movie/upcoming"),
    NOW_PLAYING_MOVIES("/movie/now_playing"),
    ON_AIR_TV_SHOWS("/tv/on_the_air"),
    TOP_RATED_TV_SHOWS("/tv/top_rated"),
    POPULAR_TV_SHOWS("/tv/popular"),
    AIRING_TODAY_TV_SHOWS("/tv/airing_today")
}

@kotlinx.serialization.Serializable
data class TmdbMovie(
    val title: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("poster_path") val poster: String? = null,
    @SerialName("backdrop_path") val backdrop: String? = null,
    val genres: List<TmdbGenre>? = null,
    val overview: String? = null,
    @SerialName("vote_average") val averageRating: Double? = null,
    @SerialName("vote_count") val totalRatings: Int? = null,
    val id: Int
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w780$poster"

    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/w1280$backdrop"
}

@kotlinx.serialization.Serializable
data class TmdbScreenshot(@SerialName("file_path") val filePath: String)

@kotlinx.serialization.Serializable
data class TmdbScreenshotList(@SerialName("backdrops") val screenshots: List<TmdbScreenshot>)

@kotlinx.serialization.Serializable
data class TmdbVideo(val key: String, val site: String, val name: String)

@kotlinx.serialization.Serializable
data class TmdbVideoList(val results: List<TmdbVideo>)

@kotlinx.serialization.Serializable
data class TmdbGenre(val id: Int, val name: String)

@kotlinx.serialization.Serializable
data class TmdbTvShow(
    val name: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("poster_path") val poster: String? = null,
    val id: Int
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w780$poster"
}

@kotlinx.serialization.Serializable
data class TmdbResultList<T>(val page: Int, val results: List<T>)
