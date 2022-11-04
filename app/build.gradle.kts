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
        buildConfigField(
            "String",
            "TmdbClientId",
            gradleLocalProperties(rootDir).getProperty("tmdbClientId")
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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.lifecycle.runtime)

    with(libs.compose) {
        implementation(activity)
        implementation(ui)
        implementation(tooling.preview)
        implementation(material3)
        implementation(icons.extended)
        implementation(navigation)
        implementation(tooling)
    }

    with(libs.accompanist) {
        implementation(pager)
        implementation(system.ui.controller)
        implementation(flowlayout)
    }

    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)

    implementation(libs.coil)

    with(libs.ktor) {
        implementation(cio)
        implementation(logging)
        implementation(content.negotiation)
        implementation(kotlinx.json)
    }

    with(libs.koin) {
        implementation(android)
        implementation(compose)
    }

    with(libs.kotlinx) {
        implementation(serialization)
        implementation(protobuf)
    }

    implementation(libs.timber)
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
