plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.feature.landing"
}

dependencies {
    implementation(project(mapOf("path" to ":common:ux")))
    with(libs.compose) {
        implementation(navigation)
    }
}
