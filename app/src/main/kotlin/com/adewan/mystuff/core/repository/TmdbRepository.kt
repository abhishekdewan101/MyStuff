package com.adewan.mystuff.core.repository

import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.model.TmdbProvider
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbScreenshotList
import com.adewan.mystuff.core.model.TmdbTvShow
import com.adewan.mystuff.core.model.TmdbVideoList
import com.adewan.mystuff.core.network.NetworkDataSource
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class TmdbRepository(private val networkDataSource: NetworkDataSource) {
    suspend fun getTmdbMovieList(tmdbListType: TmdbListType): TmdbResultList<TmdbMovie> {
        return networkDataSource.requestTmdbMovieList(tmdbListType)
    }

    suspend fun getTmdbTvShowList(tmdbListType: TmdbListType): TmdbResultList<TmdbTvShow> {
        return networkDataSource.requestTmdbTvShowList(tmdbListType)
    }

    suspend fun getTmdbMovieDetails(identifier: String): TmdbMovie {
        return networkDataSource.requestTmdbMovieDetails(identifier = identifier)
    }

    suspend fun getTmdbMovieScreenshots(identifier: String): TmdbScreenshotList {
        return networkDataSource.requestTmdbMovieScreenshots(identifier = identifier)
    }

    suspend fun getTmdbMovieVideos(identifier: String): TmdbVideoList {
        return networkDataSource.requestTmdbMovieVideos(identifier = identifier)
    }

    suspend fun getSimilarTmdbMovies(identifier: String): TmdbResultList<TmdbMovie> {
        return networkDataSource.requestSimilarTmdbMovie(identifier = identifier)
    }

    suspend fun getTmdbMovieProviders(identifier: String): List<TmdbProvider> {
        val list = mutableListOf<TmdbProvider>()
        val json = Json { ignoreUnknownKeys = true }
        val providers = networkDataSource.requestTmdbMovieProviders(identifier = identifier)
        val usProviders = providers["results"]?.jsonObject?.get("US")?.jsonObject
        usProviders?.let {
            it["flatrate"]?.jsonArray?.forEach { element ->
                list.add(json.decodeFromJsonElement(element))
            }
            it["buy"]?.jsonArray?.forEach { element ->
                list.add(json.decodeFromJsonElement(element))
            }
            it["rent"]?.jsonArray?.forEach { element ->
                list.add(json.decodeFromJsonElement(element))
            }
        }
        return list.distinct()
    }

    suspend fun getTmdbTvShowDetails(identifier: String): TmdbTvShow {
        return networkDataSource.requestTmdbTvShowDetails(identifier = identifier)
    }

    suspend fun getTmdbTvShowScreenshots(identifier: String): TmdbScreenshotList {
        return networkDataSource.requestTmdbTvShowScreenshots(identifier = identifier)
    }

    suspend fun getTmdbTvShowVideos(identifier: String): TmdbVideoList {
        return networkDataSource.requestTmdbTvShowsVideos(identifier = identifier)
    }

    suspend fun getSimilarTmdbTvShows(identifier: String): TmdbResultList<TmdbTvShow> {
        return networkDataSource.requestSimilarTmdbTvShows(identifier = identifier)
    }

    suspend fun getTmdbTvShowProviders(identifier: String): List<TmdbProvider> {
        val list = mutableListOf<TmdbProvider>()
        val json = Json { ignoreUnknownKeys = true }
        val providers = networkDataSource.requestTmdbMovieProviders(identifier = identifier)
        val usProviders = providers["results"]?.jsonObject?.get("US")?.jsonObject
        usProviders?.let {
            it["flatrate"]?.jsonArray?.forEach { element ->
                list.add(json.decodeFromJsonElement(element))
            }
            it["buy"]?.jsonArray?.forEach { element ->
                list.add(json.decodeFromJsonElement(element))
            }
            it["rent"]?.jsonArray?.forEach { element ->
                list.add(json.decodeFromJsonElement(element))
            }
        }
        return list.distinct()
    }
}
