package com.adewan.mystuff.core.model

fun buildGameDetailQuery(slug: String) =
    "f slug, name, summary,total_rating,total_rating_count,storyline,themes.*, cover.*,genres.*, videos.*, screenshots.image_id, platforms.*, platforms.platform_logo.*, similar_games.*, similar_games.cover.image_id, artworks.image_id, involved_companies.*,involved_companies.company.*, dlcs.*, dlcs.cover.image_id, dlcs.screenshots.image_id, expansions.*, expansions.cover.image_id, expansions.screenshots.image_id, remasters.*, remasters.cover.image_id, remasters.screenshots.image_id, collection.*, collection.games.*, collection.games.cover.image_id, collection.games.screenshots.image_id;" +
        "w slug = \"$slug\";"
