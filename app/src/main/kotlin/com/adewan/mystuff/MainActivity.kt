package com.adewan.mystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.adewan.mystuff.common.theme.MyStuffTheme
import com.adewan.mystuff.common.ux.ThemedContainer
import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.core.navigation.MsApp
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStuffTheme {
                ThemedContainer {
                    val repository: AuthenticationRepository = get()
                    val authState = remember { mutableStateOf(false) }
                    LaunchedEffect(key1 = repository) {
                        repository.getAndSaveAuthenticationToken()
                        authState.value = true
                    }
                    if (authState.value) {
                        MsApp()
                    }
                }
            }
        }
    }
}
