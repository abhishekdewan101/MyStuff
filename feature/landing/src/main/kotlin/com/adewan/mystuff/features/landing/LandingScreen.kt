package com.adewan.mystuff.features.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adewan.mystuff.common.annotations.ThemedPreview
import com.adewan.mystuff.common.theme.MyStuffTheme
import com.adewan.mystuff.common.ux.ThemedContainer
import com.adewan.mystuff.feature.landing.R
import com.adewan.mystuff.features.landing.navigation.Slide
import com.adewan.mystuff.features.landing.navigation.SlideData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

internal val slideData = listOf(
    SlideData(
        image = R.drawable.gaming_illus,
        title = "Find your next adventure",
        message = "Discover and track new and interesting games being released, save them to your device and be recommended what to play next !"
    ),
    SlideData(
        image = R.drawable.movie,
        title = "What to watch next",
        message = "Finding new movies and tv show to watch has never been easier. Get recommended new and up coming shows to watch next"
    ),
    SlideData(
        image = R.drawable.sharing,
        title = "Share with others",
        message = "Once you've found your next fix, share it with friends and be able to collaborate on what you should watch/play next"
    )
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun LandingScreen(navigateToExplore: () -> Unit) {
    val pagerState = rememberPagerState()
    ThemedContainer {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.7f))
                Text(
                    "MyStuff",
                    modifier = Modifier.padding(top = 20.dp),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.weight(0.7f))
                HorizontalPager(
                    count = 3,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    Slide(data = slideData[page])
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .padding(top = 20.dp)
                )

                Spacer(modifier = Modifier.weight(0.5f))

                FilledTonalButton(
                    onClick = navigateToExplore,
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                ) {
                    Text("Let's go!")
                }
            }
        }
    }
}

@ThemedPreview
@Composable
fun PreviewLandingScreen() {
    MyStuffTheme { LandingScreen {} }
}
