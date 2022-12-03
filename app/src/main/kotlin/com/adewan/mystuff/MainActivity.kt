package com.adewan.mystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.adewan.mystuff.common.theme.MyStuffTheme
import com.adewan.mystuff.common.ux.ThemedContainer
import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.core.data.repositories.OnBoardingRepository
import com.adewan.mystuff.core.navigation.MsApp
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStuffTheme {
                ThemedContainer {
                    val repository: AuthenticationRepository = get()
                    val onBoardingRepository: OnBoardingRepository = get()

                    LaunchedEffect(key1 = Unit) {
                        if (!repository.isUserAuthenticated()) repository.getAndSaveAuthenticationToken()
                    }
                    MsApp(userOnBoarded = onBoardingRepository.hasUserOnBoarded())
                }
            }
        }
    }
}
