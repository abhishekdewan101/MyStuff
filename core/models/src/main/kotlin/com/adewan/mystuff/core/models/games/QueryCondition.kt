package com.adewan.mystuff.core.models.games

enum class QueryCondition(val value: String) {
    GREATER_THAN(">"),
    GREATER_THAN_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<="),
    NOT_EQUAL("!="),
    EQUAL("="),
    AND("&"),
    OR("|")
}
