package com.adewan.mystuff.core.network

import com.adewan.mystuff.BuildConfig
import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbScreenshotList
import com.adewan.mystuff.core.model.TmdbTvShow
import com.adewan.mystuff.core.model.TmdbVideoList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

class NetworkDataSource(private val client: HttpClient) {
    suspend fun requestTmdbMovieList(tmdbListType: TmdbListType): TmdbResultList<TmdbMovie> {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3${tmdbListType.endpoint}")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }

    suspend fun requestTmdbTvShowList(tmdbListType: TmdbListType): TmdbResultList<TmdbTvShow> {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3${tmdbListType.endpoint}")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }

    suspend fun requestTmdbTvShowDetails(identifier: String): TmdbTvShow {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/tv/$identifier")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }

    suspend fun requestTmdbTvShowProviders(identifier: String): JsonObject {
        return Json.decodeFromJsonElement(
            client.get {
                url {
                    takeFrom("https://api.themoviedb.org/3/tv/$identifier/watch/providers")
                    parameter("api_key", BuildConfig.TmdbClientId)
                }
            }.body(),
        )
    }

    suspend fun requestSimilarTmdbTvShows(identifier: String): TmdbResultList<TmdbTvShow> {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/tv/$identifier/similar")
                parameter("api_key", BuildConfig.TmdbClientId)
            }
        }.body()
    }

    suspend fun requestTmdbTvShowScreenshots(identifier: String): TmdbScreenshotList {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/tv/$identifier/images")
                parameter("api_key", BuildConfig.TmdbClientId)
            }
        }.body()
    }

    suspend fun requestTmdbTvShowsVideos(identifier: String): TmdbVideoList {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/tv/$identifier/videos")
                parameter("language", "en-US")
                parameter("api_key", BuildConfig.TmdbClientId)
            }
        }.body()
    }

    suspend fun requestTmdbMovieDetails(identifier: String): TmdbMovie {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/movie/$identifier")
                parameter("api_key", BuildConfig.TmdbClientId)
                parameter("language", "en-US")
                parameter("region", "US")
            }
        }.body()
    }

    suspend fun requestTmdbMovieProviders(identifier: String): JsonObject {
        return Json.decodeFromJsonElement(
            client.get {
                url {
                    takeFrom("https://api.themoviedb.org/3/movie/$identifier/watch/providers")
                    parameter("api_key", BuildConfig.TmdbClientId)
                }
            }.body(),
        )
    }

    suspend fun requestSimilarTmdbMovie(identifier: String): TmdbResultList<TmdbMovie> {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/movie/$identifier/similar")
                parameter("api_key", BuildConfig.TmdbClientId)
            }
        }.body()
    }

    suspend fun requestTmdbMovieScreenshots(identifier: String): TmdbScreenshotList {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/movie/$identifier/images")
                parameter("api_key", BuildConfig.TmdbClientId)
            }
        }.body()
    }

    suspend fun requestTmdbMovieVideos(identifier: String): TmdbVideoList {
        return client.get {
            url {
                takeFrom("https://api.themoviedb.org/3/movie/$identifier/videos")
                parameter("language", "en-US")
                parameter("api_key", BuildConfig.TmdbClientId)
            }
        }.body()
    }
}
