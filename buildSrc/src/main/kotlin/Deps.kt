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
            const val navigation = "androidx.navigation:navigation-compose:2.5.2"
        }
    }

    object kotlinx {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0"
        const val protobuf = "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.4.0"
    }

    object accompanist {
        const val version = "0.25.1"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object koin {
        const val version = "3.2.2"
        const val android = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:3.2.1"
    }

    object ktor {
        const val version = "2.1.1"
        const val core = "io.ktor:ktor-server-core:$version"
        const val cio = "io.ktor:ktor-client-cio:$version"
        const val android = "io.ktor:ktor-client-android:$version"
        const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
        const val logging = "io.ktor:ktor-client-logging-jvm:$version"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val kotlinx_json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0'"
    }

    object protobuf {
        const val version = "3.21.5"
        const val javalite = "com.google.protobuf:protobuf-javalite:$version"
        const val kotlinlite = "com.google.protobuf:protobuf-kotlin-lite:$version"
    }

    object testing {
        const val jUnit = "junit:junit:4.13.2"
        const val jUnitExt = "androidx.test.ext:junit:1.1.3"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    }

    const val timber = "com.jakewharton.timber:timber:5.0.1"
}
