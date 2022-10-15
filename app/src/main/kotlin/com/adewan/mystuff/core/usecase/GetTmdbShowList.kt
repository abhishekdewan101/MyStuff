package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.model.TmdbListType
import com.adewan.mystuff.core.model.TmdbResultList
import com.adewan.mystuff.core.model.TmdbTvShow
import com.adewan.mystuff.core.repository.TmdbRepository

class GetTmdbShowList(val tmdbRepository: TmdbRepository) {
    suspend operator fun invoke(tmdbListType: TmdbListType): TmdbResultList<TmdbTvShow> {
        return tmdbRepository.getTmdbTvShowList(tmdbListType)
    }
}
