package com.adewan.mystuff.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashView(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = getViewModel(),
    navigateToLibrary: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    if (viewState == SplashViewState.Authenticated) {
        navigateToLibrary()
    } else {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "MyStuff",
                    fontWeight = FontWeight.Bold
                )
            })
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.4f))
                Image(
                    painter = painterResource(id = R.drawable.gaming_illus),
                    contentDescription = "",
                    modifier = Modifier
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    text = "Discover and keep track of your favorite games and get recommendations when you need to find your next big adventure.",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(0.1f))
                FilledTonalButton(onClick = { viewModel.authenticateUser() }) {
                    if (viewState is SplashViewState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp))
                    } else {
                        Text(text = "Let's Go")
                    }
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
    }
}
