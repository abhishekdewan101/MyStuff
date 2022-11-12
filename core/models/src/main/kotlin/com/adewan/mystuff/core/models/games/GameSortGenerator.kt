package com.adewan.mystuff.core.models.games

class GameSortGenerator(
    var field: QueryField? = null,
    var order: QuerySortOrder? = null
) {
    override fun toString(): String {
        return "${field?.fieldName} ${order?.name?.lowercase()}"
    }
}

inline fun querySort(block: GameSortGenerator.() -> Unit) =
    GameSortGenerator().apply(block)
