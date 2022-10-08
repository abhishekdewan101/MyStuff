package com.adewan.mystuff.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.core.repository.AuthenticationRepository
import com.adewan.mystuff.ui.composables.ThemedContainer
import com.adewan.mystuff.ui.navigation.NavigationGraph
import org.koin.androidx.compose.get

@Composable
fun SplashScreen(authenticationRepository: AuthenticationRepository = get()) {
    var isAuthenticated by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = authenticationRepository) {
        isAuthenticated = authenticationRepository.initializeIgdbAuthenticationToken()
    }
    if (isAuthenticated) {
        NavigationGraph()
    } else {
        ThemedContainer {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "My Stuff",
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                CircularProgressIndicator()
            }
        }
    }
}
