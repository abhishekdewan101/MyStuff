package com.adewan.mystuff.core.model

enum class TvListType(val endPoint: String) {
    SHOwCASE("/tv/on_the_air"),
    TOP_RATED("/tv/top_rated"),
    COMING_SOON("/tv/popular"),
    RECENTLY_RELEASED("/tv/airing_today")
}
