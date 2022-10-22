package com.adewan.mystuff.ui.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.mystuff.R
import com.adewan.mystuff.core.repository.AuthenticationRepository
import com.adewan.mystuff.ui.composables.ThemedContainer
import com.adewan.mystuff.ui.navigation.NavigationDirector
import com.adewan.mystuff.ui.utils.fakes.FakeAuthenticationRepository
import com.adewan.mystuff.ui.utils.fakes.FakeNavigationDirector
import org.koin.androidx.compose.get

@Composable
fun SplashScreen(
    navigationDirector: NavigationDirector,
    authenticationRepository: AuthenticationRepository = get()
) {
    var isAuthenticated by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = authenticationRepository) {
        isAuthenticated = authenticationRepository.initializeIgdbAuthenticationToken()
    }

    LaunchedEffect(key1 = isAuthenticated) {
        if (isAuthenticated) {
            navigationDirector.navigateToHome()
        }
    }
    ThemedContainer {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(40.dp)
            ) {
                AsyncImage(
                    model = R.mipmap.ic_launcher,
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)

                )
                Text(
                    "My Stuff",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            LinearProgressIndicator(modifier = Modifier.width(100.dp))
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES) // FIXME: For some reason this doesn't do anything :/
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        navigationDirector = FakeNavigationDirector(),
        authenticationRepository = FakeAuthenticationRepository()
    )
}
