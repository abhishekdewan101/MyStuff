plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.feature.explore"
}

dependencies {
    with(libs.compose) {
        implementation(navigation)
    }
}
