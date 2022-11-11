plugins {
    id("mystuff.android.library")
}

android {
    namespace = "com.adewan.mystuff.core.data"
}

dependencies {
    implementation(project(mapOf("path" to ":core:datastore")))
    implementation(project(mapOf("path" to ":core:network")))
    implementation(project(mapOf("path" to ":core:models")))

    with(libs.koin) {
        implementation(core)
    }
}
