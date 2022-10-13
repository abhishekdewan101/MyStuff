package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.model.MovieList
import com.adewan.mystuff.core.model.MovieListType
import com.adewan.mystuff.core.repository.MovieRepository

class GetRecentReleasedMovies(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): MovieList {
        return movieRepository.getMoviePosters(MovieListType.RECENTLY_RELEASED)
    }
}
