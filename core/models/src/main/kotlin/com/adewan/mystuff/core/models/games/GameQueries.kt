package com.adewan.mystuff.core.models.games

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

private val localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"))
private val platformString =
    PlatformIds.run { "($IOS, $ANDROID,$PS5, $PS4, $SWITCH,$XBOX, $XBOX_ONE)" }

fun GameConditionGenerator.filterFullAndDlcGames() {
    addCondition(
        lhs = QueryField.CATEGORY,
        condition = QueryCondition.EQUAL,
        rhs = "(0, 1)"
    )
}

fun buildSearchQuery(searchTerm: String) = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
        field(QueryField.FIRST_RELEASE_DATE)
        field(QueryField.TOTAL_RATING_COUNT)
        field(QueryField.TOTAL_RATING)
    }
    condition = queryConditions {
        filterFullAndDlcGames()
    }
    search = "\"$searchTerm\""
    limit = 50
}

val mostHypedGamesForNext6Months = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.plusMonths(6).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.HYPES,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = "10"
        )
        join(QueryCondition.AND)
        filterFullAndDlcGames()
    }
    sort = querySort {
        field = QueryField.HYPES
        order = QuerySortOrder.DESC
    }
    limit = 20
}

val topRatedGamesForLast2Years = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
        field(QueryField.RATING)
        field(QueryField.TOTAL_RATING)
        field(QueryField.THEMES_NAME)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.RATING,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = "70"
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.minusMonths(24).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.PLATFORM,
            condition = QueryCondition.EQUAL,
            rhs = platformString
        )
        join(QueryCondition.AND)
        filterFullAndDlcGames()
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}

val gamesComingInTheNext6Months = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
        field(QueryField.HYPES)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.HYPES,
            condition = QueryCondition.GREATER_THAN,
            rhs = "0"
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.plusMonths(6).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.PLATFORM,
            condition = QueryCondition.EQUAL,
            rhs = platformString
        )
        join(QueryCondition.AND)
        filterFullAndDlcGames()
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}

val gamesReleasedInTheLast6Month = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.minusMonths(6).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.RATING,
            condition = QueryCondition.GREATER_THAN,
            rhs = "70"
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.PLATFORM,
            condition = QueryCondition.EQUAL,
            rhs = platformString
        )
        join(QueryCondition.AND)
        filterFullAndDlcGames()
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}

val openWorldGames = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.THEMES,
            condition = QueryCondition.EQUAL,
            rhs = "(${ThemeIds.OPEN_WORLD})"
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.minusYears(2).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.PLATFORM,
            condition = QueryCondition.EQUAL,
            rhs = platformString
        )
        join(QueryCondition.AND)
        filterFullAndDlcGames()
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}

val scienceFictionGames = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.THEMES,
            condition = QueryCondition.EQUAL,
            rhs = "(${ThemeIds.SCIENCE_FICTION})"
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.minusYears(2).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.PLATFORM,
            condition = QueryCondition.EQUAL,
            rhs = platformString
        )
        join(QueryCondition.AND)
        filterFullAndDlcGames()
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}

fun buildDetailsQuery(slug: String) = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.TOTAL_RATING)
        field(QueryField.TOTAL_RATING_COUNT)
        field(QueryField.STORYLINE)
        field(QueryField.THEMES_NAME)
        field(QueryField.ARTWORKS_IMAGE_ID)
        field(QueryField.SUMMARY)
        field(QueryField.COVER_IMAGE_ID)
        field(QueryField.GENRE_NAME)
        field(QueryField.FIRST_RELEASE_DATE)
        field(QueryField.GAME_MODES_NAME)
        field(QueryField.GENRE_SLUG)
        field(QueryField.VIDEOS_ID)
        field(QueryField.VIDEOS_NAME)
        field(QueryField.SCREENSHOTS_ID)
        field(QueryField.PLATFORM_LOGO_IMAGE_ID)
        field(QueryField.SIMILAR_GAMES_SLUG)
        field(QueryField.SIMILAR_GAMES_NAME)
        field(QueryField.SIMILAR_GAMES_TOTAL_RATING_COUNT)
        field(QueryField.SIMILAR_GAMES_TOTAL_RATING)
        field(QueryField.SIMILAR_GAMES_SUMMARY)
        field(QueryField.SIMILAR_GAMES_NAME)
        field(QueryField.SIMILAR_GAMES_COVER_MAGE_ID)
    }

    condition = queryConditions {
        addCondition(lhs = QueryField.SLUG, condition = QueryCondition.EQUAL, rhs = "\"$slug\"")
    }
}
