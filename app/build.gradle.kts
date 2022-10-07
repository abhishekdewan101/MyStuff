import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf") version "0.8.12"
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.adewan.mystuff"
    compileSdk = 33

    sourceSets {
        getByName("main") {
            protobuf {
            }
        }
    }

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

        buildConfigField(
            "String",
            "ClientId",
            gradleLocalProperties(rootDir).getProperty("clientId")
        )
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
    implementation(Deps.androidx.compose.iconsExtended)

    implementation(Deps.androidx.compose.navigation)
    implementation(Deps.accompanist.systemUiController)

    testImplementation(Deps.testing.jUnit)
    androidTestImplementation(Deps.testing.jUnitExt)
    androidTestImplementation(Deps.testing.espressoCore)
    androidTestImplementation(Deps.androidx.compose.uiTestJunit4)
    debugImplementation(Deps.androidx.compose.tooling)
    debugImplementation(Deps.androidx.compose.uiTestManifest)

    implementation(Deps.protobuf.javalite)
    implementation(Deps.protobuf.kotlinlite)

    with(Deps.ktor) {
        implementation(core)
        implementation(cio)
        implementation(logging)
        implementation(contentNegotiation)
        implementation(kotlinx_json)
    }

    with(Deps.koin) {
        implementation(android)
        implementation(compose)
    }

    with(Deps.kotlinx) {
        implementation(serialization)
        implementation(protobuf)
    }

    implementation(Deps.timber)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.5"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("kotlin") {
                    option("lite")
                }
                create("java") {
                    option("lite")
                }
            }
        }
    }
}
