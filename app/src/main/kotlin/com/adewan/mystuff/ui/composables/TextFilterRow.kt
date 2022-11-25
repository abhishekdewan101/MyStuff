package com.adewan.mystuff.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.ux.NoRippleInteractionSource

data class TextFilterRowItem<T>(val label: String, val filter: T)

@Composable
fun <T> TextFilterRow(
    modifier: Modifier = Modifier,
    filters: List<TextFilterRowItem<T>>,
    initialSelectedIndex: Int = 0,
    onFilterSelected: (T) -> Unit,
) {
    var selectedIndex by rememberSaveable { mutableStateOf(initialSelectedIndex) }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        filters.forEachIndexed { index, filter ->
            val color = if (index == selectedIndex) {
                MaterialTheme.colorScheme.onBackground
            } else {
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            }
            Text(
                filter.label,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = color,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .clickable(
                        interactionSource = NoRippleInteractionSource,
                        indication = null,
                        onClick = {
                            selectedIndex = index
                            onFilterSelected(filter.filter)
                        },
                    )
                    .padding(10.dp),
            )
        }
    }
}
