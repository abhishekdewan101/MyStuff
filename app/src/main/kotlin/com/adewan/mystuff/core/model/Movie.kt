package com.adewan.mystuff.core.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Movie(
    val title: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("poster_path") val poster: String? = null
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w780$poster"
}

@kotlinx.serialization.Serializable
data class MovieList(
    val page: Int,
    @SerialName("results") val movies: List<Movie>
)
