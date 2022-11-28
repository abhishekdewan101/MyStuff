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
    implementation(project(mapOf("path" to ":feature:search")))
    implementation(project(mapOf("path" to ":feature:account")))
    implementation(project(mapOf("path" to ":feature:library")))
    implementation(project(mapOf("path" to ":common:ux")))
    with(libs.compose) {
        implementation(navigation)
    }
}
