import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("mystuff.android.application")
    id("mystuff.android.application.compose")
    id("com.google.protobuf") version "0.8.12"
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.adewan.mystuff"

    defaultConfig {
        applicationId = "com.adewan.mystuff"
        minSdk = 27
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "ClientId",
            gradleLocalProperties(rootDir).getProperty("clientId"),
        )
        buildConfigField(
            "String",
            "TmdbClientId",
            gradleLocalProperties(rootDir).getProperty("tmdbClientId"),
        )
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
