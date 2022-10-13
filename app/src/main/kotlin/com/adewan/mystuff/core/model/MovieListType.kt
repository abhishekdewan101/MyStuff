package com.adewan.mystuff.core.model

enum class MovieListType(val endPoint: String) {
    SHOwCASE("/movie/popular"),
    TOP_RATED("/movie/top_rated"),
    COMING_SOON("/movie/upcoming"),
    RECENTLY_RELEASED("/movie/now_playing")
}
