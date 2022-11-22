package com.adewan.mystuff.common.ux

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipsRow(chips: List<String>, color: Color, textColor: Color) {
    FlowRow(modifier = Modifier.fillMaxWidth(), mainAxisSpacing = 5.dp) {
        chips.forEach { label ->
            ElevatedAssistChip(
                onClick = { },
                label = { Text(text = label, style = MaterialTheme.typography.bodySmall) },
                colors = AssistChipDefaults.elevatedAssistChipColors(
                    containerColor = color,
                    labelColor = textColor
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewChipsRow() {
    ChipsRow(
        chips = listOf("Horror", "Action", "Puzzle"),
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        textColor = MaterialTheme.colorScheme.secondaryContainer
    )
}
