package com.adewan.mystuff.core.usecase

import com.adewan.mystuff.core.repository.GameRepository
import proto.GameResult
import java.time.LocalDateTime
import java.time.ZoneOffset

class GetComingSoonGames(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): GameResult {
        val query =
            "f slug, name,cover.image_id,first_release_date,platforms.id, platforms.name, platforms.slug, screenshots.image_id;" +
                "w hypes >= 0 & first_release_date >= ${
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                };" +
                "s hypes desc;" +
                "l 20;"
        return gameRepository.getGameCovers(forQuery = query)
    }
}
