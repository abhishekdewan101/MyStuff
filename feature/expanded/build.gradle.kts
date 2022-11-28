plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.adewan.mystuff.feature.expanded"
}

dependencies {
    implementation(project(mapOf("path" to ":common:ux")))
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:models")))
    with(libs.compose) {
        implementation(navigation)
    }

    with(libs.accompanist) {
        implementation(pager)
        implementation(pager.indicators)
    }

    with(libs.koin) {
        implementation(android)
        implementation(compose)
    }

    with(libs.kotlinx) {
        implementation(serialization)
        implementation(protobuf)
    }

    implementation(libs.coil)
}
