package com.adewan.mystuff.common.ux

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_illus),
            contentDescription = message,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}
