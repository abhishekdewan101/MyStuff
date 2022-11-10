plugins {
    id("mystuff.android.library")
}

android {
    namespace = "com.adewan.mystuff.core.di"
}

dependencies {
    implementation(project(mapOf("path" to ":core:datastore")))
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:network")))

    with(libs.koin) {
        implementation(core)
    }
}
