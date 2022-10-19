package com.adewan.mystuff.core.model

import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneOffset

val SHOWCASE_GAMES_QUERY =
    "f slug, name,cover.image_id,first_release_date,platforms.id, platforms.name, platforms.slug, screenshots.image_id;" +
        "w rating >= 75 & hypes > 0 & first_release_date <= ${
        Clock.systemDefaultZone().instant().epochSecond
        } & first_release_date > ${
        LocalDateTime.now().minusMonths(6).toEpochSecond(ZoneOffset.UTC)
        };" +
        "s ratings desc;" +
        "l 20;"

val TOP_RATED_GAMES_QUERY =
    "f slug, name,cover.image_id,first_release_date,platforms.id, platforms.name, platforms.slug, screenshots.image_id;" +
        "w rating >= 80 & first_release_date >= ${
        LocalDateTime.now().minusYears(1).toEpochSecond(ZoneOffset.UTC)
        };" +
        "s ratings desc;" +
        "l 20;"

val COMING_SOON_GAMES_QUERY =
    "f slug, name,cover.image_id,first_release_date,platforms.id, platforms.name, platforms.slug, screenshots.image_id;" +
        "w hypes >= 0 & first_release_date >= ${
        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        };" +
        "s hypes desc;" +
        "l 20;"

val RECENT_RELEASED_GAMES_QUERY =
    "f slug, name,cover.image_id,first_release_date,platforms.id, platforms.name, platforms.slug, screenshots.image_id;" +
        "w total_rating >= 60 & first_release_date <= ${
        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        };" +
        "s first_release_date desc;" +
        "l 20;"

fun buildGameDetailQuery(slug: String) =
    "f slug, name, summary,total_rating,total_rating_count,storyline,themes.*, cover.*,genres.*, videos.*, screenshots.image_id, platforms.id, platforms.name, platforms.slug, similar_games.*, similar_games.screenshots.image_id, similar_games.cover.image_id, artworks.image_id, involved_companies.*,involved_companies.company.*, dlcs.*, dlcs.cover.image_id, dlcs.screenshots.image_id, expansions.*, expansions.cover.image_id, expansions.screenshots.image_id, remasters.*, remasters.cover.image_id, remasters.screenshots.image_id, collection.*, collection.games.*, collection.games.cover.image_id, collection.games.screenshots.image_id;" +
        "w slug = \"$slug\";"
