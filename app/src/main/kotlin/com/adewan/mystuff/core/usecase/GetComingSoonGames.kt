package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.repository.GameRepository
import proto.GameResult
import java.time.Clock

class GetComingSoonGames(private val gameRepository: GameRepository) {
    suspend operator fun invoke(clock: Clock): GameResult {
        val query =
            "f slug, name, cover.image_id,first_release_date,platforms.id, platforms.name, platforms.slug, screenshots.image_id;" +
                "w hypes > 0 & first_release_date >= ${clock.instant().epochSecond};" +
                "s first_release_date asc;" +
                "l 50;"
        return gameRepository.getGameCovers(forQuery = query)
    }
}
