plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.adewan.mystuff"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.adewan.mystuff"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // FIXME: Add a release target that minify's the build

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

    implementation(Deps.androidx.coreKtx)
    implementation(Deps.androidx.lifecycleRuntime)
    implementation(Deps.androidx.compose.activityCompose)
    implementation(Deps.androidx.compose.ui)
    implementation(Deps.androidx.compose.toolingPreview)
    implementation(Deps.androidx.compose.material3)

    implementation(Deps.androidx.compose.navigation)
    implementation(Deps.androidx.accompanist.systemUiController)

    testImplementation(Deps.testing.jUnit)
    androidTestImplementation(Deps.testing.jUnitExt)
    androidTestImplementation(Deps.testing.espressoCore)
    androidTestImplementation(Deps.androidx.compose.uiTestJunit4)
    debugImplementation(Deps.androidx.compose.tooling)
    debugImplementation(Deps.androidx.compose.uiTestManifest)
}
