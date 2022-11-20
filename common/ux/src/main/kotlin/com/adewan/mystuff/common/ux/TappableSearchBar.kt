package com.adewan.mystuff.common.ux

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.theme.MyStuffTheme

@Composable
fun TappableSearchBar(modifier: Modifier = Modifier, onTap: () -> Unit) {
    Box(
        modifier = modifier
            .clickable { onTap() }
            .height(56.dp) // Default text field height
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(32.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewTappableSearchBar() {
    MyStuffTheme {
        Scaffold(topBar = {
            TappableSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            ) {
            }
        }, modifier = Modifier.fillMaxSize()) {}
    }
}
