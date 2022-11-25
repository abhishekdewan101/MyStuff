plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.adewan.mystuff.feature.games"
}

dependencies {
    implementation(project(mapOf("path" to ":common:ux")))
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:models")))
    with(libs.compose) {
        implementation(navigation)
    }

    with(libs.kotlinx) {
        implementation(serialization)
        implementation(protobuf)
    }

    with(libs.accompanist) {
        implementation(pager)
    }

    with(libs.koin) {
        implementation(android)
        implementation(compose)
    }

    implementation(libs.coil)
}
