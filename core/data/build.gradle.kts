plugins {
    id("mystuff.android.library")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.adewan.mystuff.core.data"
}

dependencies {
    implementation(project(mapOf("path" to ":core:datastore")))
    implementation(project(mapOf("path" to ":core:network")))
    implementation(project(mapOf("path" to ":core:models")))
    implementation(project(mapOf("path" to ":core:database")))

    with(libs.kotlinx.coroutines) {
        implementation(android)
    }

    with(libs.koin) {
        implementation(core)
    }

    with(libs.firebase) {
        implementation(platform(bom))
        implementation(auth)
    }

    implementation("com.google.android.gms:play-services-auth:20.4.0")
}
