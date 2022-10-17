package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.repository.IgdbRepository
import proto.Game

class GetGameDetails(private val igdbRepository: IgdbRepository) {
    suspend operator fun invoke(identifier: String): Game {
        return igdbRepository.getGameDetails(forIdentifier = identifier)
    }
}
