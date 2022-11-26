package com.adewan.mystuff.common.ux

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class ChipsRowItem<T>(
    val label: String,
    val icon: ImageVector = Icons.Default.Check,
    val id: T
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SingleSelectFilterChipRow(
    modifier: Modifier = Modifier,
    chips: List<ChipsRowItem<T>>,
    onTap: (T) -> Unit
) {
    var selectedChip by remember { mutableStateOf(chips[0]) }
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(chips) { chip ->
            val selected = chip.id == selectedChip.id
            FilterChip(
                selected = selected,
                onClick = {
                    onTap(chip.id)
                    selectedChip = chip
                },
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (selected) {
                            Icon(
                                imageVector = chip.icon,
                                contentDescription = "Selected",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 5.dp)
                            )
                        }
                        Text(text = chip.label)
                    }
                }
            )
        }
    }
}
