package com.adewan.mystuff.core.repository

import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbTvShow
import com.adewan.mystuff.core.network.NetworkDataSource

class TmdbRepository(private val networkDataSource: NetworkDataSource) {
    suspend fun getTmdbMovieList(tmdbListType: TmdbListType): TmdbResultList<TmdbMovie> {
        return networkDataSource.requestTmdbMovieList(tmdbListType)
    }

    suspend fun getTmdbTvShowList(tmdbListType: TmdbListType): TmdbResultList<TmdbTvShow> {
        return networkDataSource.requestTmdbTvShowList(tmdbListType)
    }
}
