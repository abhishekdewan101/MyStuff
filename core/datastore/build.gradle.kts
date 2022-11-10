plugins {
    id("mystuff.android.library")
    kotlin("plugin.serialization") version "1.7.10"
}

android {
    namespace = "com.adewan.mystuff.datastore"
}

dependencies {
    implementation(libs.androidx.datastore)

    with(libs.koin) {
        implementation(android)
    }

    with(libs.kotlinx) {
        implementation(serialization)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
