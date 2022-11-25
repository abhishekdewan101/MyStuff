package com.adewan.mystuff.core.models.games

data class GameQuery(
    var fields: GameFieldGenerator? = null,
    var condition: GameConditionGenerator? = null,
    var sort: GameSortGenerator? = null,
    var limit: Int? = null
) {

    fun buildQuery(): String {
        val builder = StringBuilder()
        fields?.let { builder.append("f $fields;") }
        condition?.let { builder.append("w $condition;") }
        sort?.let { builder.append("s $sort;") }
        limit?.let { builder.append("l $limit;") }
        return builder.toString()
    }
}

inline fun gameQuery(block: GameQuery.() -> Unit) = GameQuery().apply(block)
