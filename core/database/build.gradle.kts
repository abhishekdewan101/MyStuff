plugins {
    id("mystuff.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.adewan.mystuff.core.database"
}

dependencies {
    implementation(project(mapOf("path" to ":core:models")))

    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    with(libs.kotlinx.coroutines) {
        implementation(android)
    }
    with(libs.koin) {
        implementation(android)
    }
}
