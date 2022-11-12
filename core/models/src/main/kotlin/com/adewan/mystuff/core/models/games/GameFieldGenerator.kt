package com.adewan.mystuff.core.models.games

class GameFieldGenerator(private val fields: MutableList<QueryField>) {
    fun field(field: QueryField) = fields.add(field)

    override fun toString(): String {
        return fields.joinToString(",") { it.fieldName }
    }
}

inline fun queryFields(block: GameFieldGenerator.() -> Unit) =
    GameFieldGenerator(mutableListOf()).apply(block)
