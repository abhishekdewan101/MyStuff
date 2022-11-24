package com.adewan.mystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.adewan.mystuff.common.theme.MyStuffTheme
import com.adewan.mystuff.core.data.repositories.AuthenticationRepository
import com.adewan.mystuff.core.navigation.AppNavHost
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyStuffTheme {
                val authRepo: AuthenticationRepository = get()
                AppNavHost(navController = rememberNavController(), authenticatedUser = authRepo.isUserAuthenticated())
            }
        }
    }
}
