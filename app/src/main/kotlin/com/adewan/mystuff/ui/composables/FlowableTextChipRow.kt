@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FlowableTextChipRow(modifier: Modifier = Modifier, chips: List<String>) {
    FlowRow(modifier = modifier, mainAxisSpacing = 5.dp) {
        chips.map {
            ElevatedAssistChip(
                onClick = { },
                label = { Text(text = it) },
            )
        }
    }
}

@Preview
@Composable
fun PreviewFlowableTextChipRow() {
    FlowableTextChipRow(
        modifier = Modifier.fillMaxSize(),
        chips = listOf(
            "Action",
            "Action2",
            "Thriller",
            "Horror",
            "Sci-Fi",
            "Thriller2",
            "Horror2",
            "Sci-Fi2",
            "Cyberpunk",
        ),
    )
}
