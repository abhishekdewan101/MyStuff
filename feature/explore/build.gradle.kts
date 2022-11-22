plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.feature.explore"
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
    }

    with(libs.koin) {
        implementation(android)
        implementation(compose)
    }
}
