package com.adewan.mystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adewan.mystuff.common.theme.MyStuffTheme
import com.adewan.mystuff.common.ux.ThemedContainer
import com.adewan.mystuff.core.navigation.MsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStuffTheme {
                ThemedContainer {
                    MsApp()
                }
            }
        }
    }
}
