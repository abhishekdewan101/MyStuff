package com.adewan.mystuff.features.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adewan.mystuff.common.ux.ThemedContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen() {
    ThemedContainer {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .systemBarsPadding()
                    .fillMaxSize()
            ) { Text(text = "Abhishek Dewan") }
        }
    }
}
