import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("mystuff.android.library")
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.adewan.mystuff.network"
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        resValue("string", "clientId", gradleLocalProperties(rootDir).getProperty("clientId"))
        resValue(
            "string",
            "tmdbClientId",
            gradleLocalProperties(rootDir).getProperty("tmdbClientId")
        )
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core:models")))

    with(libs.kotlinx) {
        implementation(serialization)
        implementation(protobuf)
    }

    with(libs.ktor) {
        implementation(cio)
        implementation(logging)
        implementation(content.negotiation)
        implementation(kotlinx.json)
    }

    with(libs.koin) {
        implementation(android)
    }
}
