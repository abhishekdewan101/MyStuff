package com.adewan.mystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adewan.mystuff.ui.navigation.NavigationGraph
import com.adewan.mystuff.ui.theme.MyStuffTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStuffTheme {
                NavigationGraph()
            }
        }
    }
}
