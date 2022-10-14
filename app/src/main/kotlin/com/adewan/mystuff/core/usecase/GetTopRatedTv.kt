package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.model.TvListType
import com.adewan.mystuff.core.model.TvShowList
import com.adewan.mystuff.core.repository.TvRepository

class GetTopRatedTv(private val tvRepository: TvRepository) {
    suspend operator fun invoke(): TvShowList {
        return tvRepository.getTvPosters(TvListType.TOP_RATED)
    }
}
