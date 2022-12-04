plugins {
    id("mystuff.android.library")
}

android {
    namespace = "com.adewan.mystuff.core.di"
}

dependencies {
    implementation(project(mapOf("path" to ":core:datastore")))
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:database")))
    implementation(project(mapOf("path" to ":core:network")))
    implementation(project(mapOf("path" to ":core:utils")))

    with(libs.koin) {
        implementation(core)
    }
}
