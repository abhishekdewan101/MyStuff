plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.feature.search"
}

dependencies {
    implementation(project(mapOf("path" to ":common:ux")))
    implementation(project(mapOf("path" to ":core:data")))
    with(libs.compose) {
        implementation(navigation)
    }

    with(libs.koin) {
        implementation(android)
        implementation(compose)
    }
    with(libs.accompanist) {
        implementation(pager)
        implementation(pager.indicators)
    }
}
