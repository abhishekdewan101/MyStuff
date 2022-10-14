package com.adewan.mystuff.core.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TvShow(
    val name: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("poster_path") val poster: String? = null
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w780$poster"
}

@kotlinx.serialization.Serializable
data class TvShowList(
    val page: Int,
    @SerialName("results") val shows: List<TvShow>
)
