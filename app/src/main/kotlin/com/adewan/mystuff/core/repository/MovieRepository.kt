package com.adewan.mystuff.core.repository

import com.adewan.mystuff.core.model.MovieList
import com.adewan.mystuff.core.model.MovieListType
import com.adewan.mystuff.core.network.NetworkDataSource

class MovieRepository(private val networkDataSource: NetworkDataSource) {
    suspend fun getMoviePosters(type: MovieListType): MovieList =
        networkDataSource.requestMovies(type = type)
}
