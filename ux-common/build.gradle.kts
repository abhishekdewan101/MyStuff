plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.adewan.ux.common"
    compileSdk = 33

    defaultConfig {
        minSdk = 27
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.androidx.compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Deps.androidx.compose.ui)
    implementation(Deps.androidx.compose.toolingPreview)
    implementation(Deps.androidx.compose.material3)
    implementation(Deps.androidx.compose.tooling)

    implementation(Deps.accompanist.systemUiController)
}
