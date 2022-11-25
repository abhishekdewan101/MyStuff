plugins {
    id("mystuff.android.library")
    id("mystuff.android.library.compose")
}

android {
    namespace = "com.adewan.mystuff.core.navigation"
}

dependencies {
    implementation(project(mapOf("path" to ":feature:explore")))
    implementation(project(mapOf("path" to ":feature:landing")))
    implementation(project(mapOf("path" to ":feature:games")))
    with(libs.compose) {
        implementation(navigation)
    }
}
