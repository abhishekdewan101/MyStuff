package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.model.TmdbMovie
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.repository.TmdbRepository

class GetTmdbMovieList(val tmdbRepository: TmdbRepository) {
    suspend operator fun invoke(tmdbListType: TmdbListType): TmdbResultList<TmdbMovie> {
        return tmdbRepository.getTmdbMovieList(tmdbListType)
    }
}
