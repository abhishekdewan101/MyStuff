package com.adewan.mystuff.core.models.games

import com.adewan.mystuff.core.models.games.GameConditionGenerator.Companion.queryConditions
import com.adewan.mystuff.core.models.games.GameFieldGenerator.Companion.queryFields
import com.adewan.mystuff.core.models.games.GameQuery.Companion.gameQuery
import com.adewan.mystuff.core.models.games.GameSortGenerator.Companion.querySort
import org.junit.Test

class GameQueryTest {
    @Test
    fun `testing parsing`() {
        val query = gameQuery {
            fields = queryFields {
                field(QueryField.SLUG)
                field(QueryField.NAME)
            }
            condition = queryConditions {
                addCondition(
                    lhs = QueryField.SLUG,
                    condition = QueryCondition.EQUAL,
                    rhs = "something"
                )
                join(QueryCondition.AND)
                addCondition(
                    lhs = QueryField.HYPES,
                    condition = QueryCondition.GREATER_THAN_EQUAL,
                    rhs = "70"
                )
            }
            sort = querySort {
                field = QueryField.FIRST_RELEASE_DATE
                order = QuerySortOrder.DESC
            }
            limit = 20
        }

        println("Abhishek ---> $query")
    }
}
