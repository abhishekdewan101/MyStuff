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
