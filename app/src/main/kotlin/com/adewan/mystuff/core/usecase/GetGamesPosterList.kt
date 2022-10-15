package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.repository.IgdbRepository
import proto.GameResult

class GetGamesPosterList(private val igdbRepository: IgdbRepository) {
    suspend operator fun invoke(query: String): GameResult {
        return igdbRepository.getGameCovers(forQuery = query)
    }
}
