object Deps {
    object androidx {
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"


        object compose {
            const val version = "1.2.0"
            const val activityCompose = "androidx.activity:activity-compose:1.5.1"
            const val ui = "androidx.compose.ui:ui:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha11"
            const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        }
    }

    object testing {
        const val jUnit = "junit:junit:4.13.2"
        const val jUnitExt = "androidx.test.ext:junit:1.1.3"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    }
}