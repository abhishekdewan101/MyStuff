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
    @SerialName("poster_path") val poster: String? = null
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w780$poster"
}

@kotlinx.serialization.Serializable
data class TmdbTvShow(
    val name: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("poster_path") val poster: String? = null
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w780$poster"
}

@kotlinx.serialization.Serializable
data class TmdbResultList<T>(val page: Int, val results: List<T>)
