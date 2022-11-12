package com.adewan.mystuff.core.models.games

class GameSortGenerator(
    var field: QueryField? = null,
    var order: QuerySortOrder? = null
) {
    companion object {
        inline fun querySort(block: GameSortGenerator.() -> Unit) =
            GameSortGenerator().apply(block)
    }

    override fun toString(): String {
        return "${field?.fieldName} ${order?.name?.lowercase()}"
    }
}
