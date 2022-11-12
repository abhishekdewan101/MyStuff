package com.adewan.mystuff.core.models.games

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

private val localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"))

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
    }
    sort = querySort {
        field = QueryField.HYPES
        order = QuerySortOrder.DESC
    }
    limit = 20
}.toString()

val topRatedGamesForLast12Months = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
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
            rhs = localDateTime.minusMonths(12).toEpochSecond(ZoneOffset.UTC).toString()
        )
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}.toString()

val gamesComingInTheNext6Months = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
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
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}.toString()

val gamesReleasedInTheLast2Month = gameQuery {
    fields = queryFields {
        field(QueryField.SLUG)
        field(QueryField.NAME)
        field(QueryField.COVER_IMAGE_ID)
    }
    condition = queryConditions {
        addCondition(
            lhs = QueryField.FIRST_RELEASE_DATE,
            condition = QueryCondition.GREATER_THAN_EQUAL,
            rhs = localDateTime.minusMonths(2).toEpochSecond(ZoneOffset.UTC).toString()
        )
        join(QueryCondition.AND)
        addCondition(
            lhs = QueryField.RATING,
            condition = QueryCondition.GREATER_THAN,
            rhs = "70"
        )
    }
    sort = querySort {
        field = QueryField.RATING
        order = QuerySortOrder.DESC
    }
    limit = 20
}.toString()
