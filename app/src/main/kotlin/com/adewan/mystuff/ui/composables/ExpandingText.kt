package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ExpandingText(text: String, closedLineCount: Int) {
    var lineCount by remember { mutableStateOf(closedLineCount) }
    Text(
        text = text,
        maxLines = lineCount,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                lineCount = if (lineCount == closedLineCount) {
                    100
                } else {
                    closedLineCount
                }
            },
    )
}
