package com.adewan.mystuff.core.models.games

class GameQuery(
    private val fields: String? = null,
    private val condition: String? = null,
    private val sort: String? = null,
    private val limit: Int? = null
) {

    private constructor(builder: Builder) : this(
        fields = builder.fields.toString(),
        condition = builder.condition.toString(),
        sort = builder.sort.toString(),
        limit = builder.limit
    )

    class Builder(
        var fields: GameFieldGenerator? = null,
        var condition: GameConditionGenerator? = null,
        var sort: GameSortGenerator? = null,
        var limit: Int? = null
    ) {
        fun build() = GameQuery(this)
    }

    companion object {
        inline fun gameQuery(block: Builder.() -> Unit) =
            Builder().apply(block).build()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        fields?.let { builder.append("f $fields;") }
        condition?.let { builder.append("w $condition;") }
        sort?.let { builder.append("s $sort;") }
        limit?.let { builder.append("l $limit;") }
        return builder.toString()
    }
}
