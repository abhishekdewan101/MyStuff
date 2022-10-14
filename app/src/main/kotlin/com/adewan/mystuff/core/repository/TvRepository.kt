package com.adewan.mystuff.core.repository

import com.adewan.mystuff.core.model.TvListType
import com.adewan.mystuff.core.model.TvShowList
import com.adewan.mystuff.core.network.NetworkDataSource

class TvRepository(private val networkDataSource: NetworkDataSource) {
    suspend fun getTvPosters(type: TvListType): TvShowList =
        networkDataSource.requestTvShow(type = type)
}
