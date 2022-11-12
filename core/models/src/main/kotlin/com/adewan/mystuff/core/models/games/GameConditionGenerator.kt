package com.adewan.mystuff.core.models.games

data class Condition(
    val lhs: QueryField? = null,
    val condition: QueryCondition,
    val rhs: String? = null
) {
    override fun toString(): String {
        return "${lhs?.fieldName ?: ""} ${condition.value} ${rhs ?: ""}"
    }
}

class GameConditionGenerator(private val conditions: MutableList<Condition>) {

    fun addCondition(lhs: QueryField, condition: QueryCondition, rhs: String) {
        conditions.add(Condition(lhs = lhs, condition = condition, rhs = rhs))
    }

    fun join(condition: QueryCondition) {
        conditions.add(Condition(lhs = null, condition = condition, rhs = null))
    }

    override fun toString(): String {
        return conditions.joinToString(" ") { it.toString() }
    }
}

inline fun queryConditions(block: GameConditionGenerator.() -> Unit) =
    GameConditionGenerator(mutableListOf()).apply(block)
