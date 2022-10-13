package com.adewan.mystuff.core.model

enum class MovieListType(val endPoint: String) {
    TOP_RATED("/movie/top_rated"),
    COMING_SOON("/movie/upcoming")
}
