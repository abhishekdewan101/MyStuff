plugins {
    id("mystuff.android.library")
}

android {
    namespace = "com.adewan.mystuff.feature.di"
}

dependencies {
    implementation(project(mapOf("path" to ":feature:explore")))
    implementation(project(mapOf("path" to ":feature:landing")))
    implementation(project(mapOf("path" to ":feature:library")))
    implementation(project(mapOf("path" to ":feature:search")))
    implementation(project(mapOf("path" to ":feature:expanded")))
    implementation(project(mapOf("path" to ":feature:details")))

    with(libs.koin) {
        implementation(core)
    }
}
