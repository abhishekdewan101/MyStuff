package com.adewan.mystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.adewan.mystuff.ui.splash.SplashScreen
import com.adewan.mystuff.ui.theme.MyStuffTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyStuffTheme {
                SplashScreen()
            }
        }
    }
}
